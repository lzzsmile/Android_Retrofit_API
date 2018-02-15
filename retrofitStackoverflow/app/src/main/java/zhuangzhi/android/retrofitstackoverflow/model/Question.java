package zhuangzhi.android.retrofitstackoverflow.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by zhuangzhili on 2018-02-14.
 */

public class Question {

    @SerializedName("title")
    public String title;

    @SerializedName("question_id")
    public String questionId;

    @Override
    public String toString() {
        return title;
    }
}
