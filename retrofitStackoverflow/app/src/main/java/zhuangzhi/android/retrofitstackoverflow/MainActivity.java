package zhuangzhi.android.retrofitstackoverflow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import zhuangzhi.android.retrofitstackoverflow.model.Answer;
import zhuangzhi.android.retrofitstackoverflow.model.Question;

public class MainActivity extends AppCompatActivity {

    private StackOverflowAPI mStackOverflowAPI;
    private Spinner mSpinner;
    private RecyclerView mRecyclerView;

    Callback<ListWrapper<Question>> questionsCallback = new Callback<ListWrapper<Question>>() {
        @Override
        public void onResponse(Call<ListWrapper<Question>> call, Response<ListWrapper<Question>> response) {
            if (response.isSuccessful()) {
                ListWrapper<Question> questions = response.body();
                ArrayAdapter<Question> arrayAdapter = new ArrayAdapter<Question>(MainActivity.this,
                        android.R.layout.simple_spinner_dropdown_item, questions.items);
                mSpinner.setAdapter(arrayAdapter);
            } else {
                Log.d("Questions Callback", "Cannot get the response for questions query!");
            }
        }

        @Override
        public void onFailure(Call<ListWrapper<Question>> call, Throwable t) {
            t.printStackTrace();
        }
    };

    Callback<ListWrapper<Answer>> answersCallback = new Callback<ListWrapper<Answer>>() {
        @Override
        public void onResponse(Call<ListWrapper<Answer>> call, Response<ListWrapper<Answer>> response) {
            if (response.isSuccessful()) {
                List<Answer> data = new ArrayList<>();
                data.addAll(response.body().items);
                mRecyclerView.setAdapter(new StackOverflowAdapter(data));
            } else {
                Log.d("Answers Callback", "Cannot get the response for answers query!");
            }
        }

        @Override
        public void onFailure(Call<ListWrapper<Answer>> call, Throwable t) {
            t.printStackTrace();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSpinner = (Spinner) findViewById(R.id.question_spinner);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Question question = (Question) adapterView.getAdapter().getItem(i);
                mStackOverflowAPI.getAnswersForQuestion(question.questionId).enqueue(answersCallback);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_list);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        createStackOverflowAPI();
        mStackOverflowAPI.getQuestions().enqueue(questionsCallback);

    }

    private void createStackOverflowAPI() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(StackOverflowAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        mStackOverflowAPI = retrofit.create(StackOverflowAPI.class);
    }

}
