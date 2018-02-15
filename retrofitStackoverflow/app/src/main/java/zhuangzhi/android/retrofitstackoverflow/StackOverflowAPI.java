package zhuangzhi.android.retrofitstackoverflow;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import zhuangzhi.android.retrofitstackoverflow.model.Answer;
import zhuangzhi.android.retrofitstackoverflow.model.Question;

/**
 * Created by zhuangzhili on 2018-02-14.
 */

public interface StackOverflowAPI {

    String BASE_URL = "http://api.stackexchange.com";

    @GET("/2.2/questions?order=desc&sort=votes&site=stackoverflow&tagged=android&filter=withbody")
    Call<ListWrapper<Question>> getQuestions();

    @GET("/2.2/questions/{id}/answers?order=desc&sort=votes&site=stackoverflow")
    Call<ListWrapper<Answer>> getAnswersForQuestion(@Path("id") String questionId);

}
