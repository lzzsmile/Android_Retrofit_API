package zhuangzhi.android.retrofitstackoverflow.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by zhuangzhili on 2018-02-14.
 */

public class Answer {

    @SerializedName("answer_id")
    public int answerId;

    @SerializedName("is_accepted")
    public boolean accepted;

    @SerializedName("score")
    public int score;

    @Override
    public String toString() {
        return answerId + " - Score: " + score + " - Accpted: " + (accepted ? "Yes" : "No");
    }
}
