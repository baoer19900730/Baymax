package com.example.zhou.watch.Heart;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.zhou.watch.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by zhou on 2017/7/13
 */

public class HeartChecked1 extends Fragment implements View.OnClickListener {

    private static final String TAG = "Baymax";

    @BindView(R.id.listView) ListView listView;
    @BindView(R.id.submit) Button submit;
    Unbinder unbinder;

    private Button heartStart;
    private RadioButton zeroScore;
    private RadioButton twoScore;
    private RadioButton fourScore;
    private RadioButton sixScore;
    public int score = 0;
    private int total = 0;

    LayoutInflater mInflater;
    AnswerAdapter mAdapter;
    SparseIntArray mArrayMap = new SparseIntArray();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.heart_exam_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        mInflater = inflater;
        mAdapter = new AnswerAdapter(getContext(), R.layout.heart_exam_fragment_item);
        listView.setAdapter(mAdapter);

        /*heartStart = (Button) view.findViewById(R.id.heart_start);
        zeroScore = (RadioButton) view.findViewById(R.id.zero_score);
        twoScore = (RadioButton) view.findViewById(R.id.two_score);
        fourScore = (RadioButton) view.findViewById(R.id.four_score);
        sixScore = (RadioButton) view.findViewById(R.id.six_score);
        zeroScore.setOnClickListener(this);
        twoScore.setOnClickListener(this);
        fourScore.setOnClickListener(this);
        sixScore.setOnClickListener(this);
        heartStart.setOnClickListener(this);*/
        return view;
    }

    /**
     * //TODO 你这样子写，是根据无法区分是点击了哪一项，根据没有任何作用。
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.zero_score:
                Log.d("1", "" + total);
                total += 1;
                break;
            case R.id.two_score:
                Log.d("2", "" + total);
                total += 1;
                score += 2;
                break;
            case R.id.four_score:
                Log.d("3", "" + total);
                total += 1;
                score += 4;
                break;
            case R.id.six_score:
                Log.d("4", "" + total);
                total += 1;
                score += 6;
                break;
            case R.id.heart_start:
//                if (total >= 10){
                ((HeartChecked) getActivity()).replaceHeart(new HeartChecked2());
//                }else {
//                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//                    builder.setTitle("Alert");
//                    builder.setMessage("请做完题目");
//                    builder.setCancelable(false);
//                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                        }
//                    });
//                    builder.show();
//                }
                break;
            default:
                break;

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.submit)
    public void onViewClicked() {
        int score = 0;
        int size = mArrayMap.size();
        if (size < 15) {
            //TODO -> 拦截，不让提交过去
        }

        for (int i = 0; i < size; i ++) {
            score += mArrayMap.valueAt(i);  //TODO -> 将所有选项的分数累加，这个score可以作为答案传递到下一页
        }
        Log.i(TAG, "onViewClicked.. size: " + size + ", score: " + score);
    }




    class AnswerAdapter extends ArrayAdapter {

        private int resId;
        private String[] array = new String[]{
                "1.悲伤：你是否一直感到伤心或悲伤?",
                "2.泄气：你是否感到前景渺茫？",
                "3.缺乏自尊心：你是否觉得自己没有价值或自认为是一个失败者？",
                "4.自卑：你是否觉得力不从心或自叹比不上别人？",
                "5.内疚：你是否对任何事都自责？",
                "6.犹豫：你是否在做决定时犹豫不决？",
                "7.焦躁不安：这段时间你是否一直处于愤怒和不满状态？",
                "8.对生活丧失兴趣：你对事业、家庭、爱好或朋友是否丧失了兴趣？",
                "9.丧失动机：你是否感到一蹶不振做事情毫无动力？",
                "10.自我印象可怜：你是否认为自己已衰老或失去魅力？",
                "11.食欲变化：你是否感到食欲不振或情不自禁地暴饮暴食？",
                "12.睡眠变化：你是否患有失眠症或整天感到体力不支、昏昏欲睡？",
                "13.丧失性欲望：你是否丧失了对性的兴趣？",
                "14.臆想症：你是否经常担心自己的健康？",
                "15.自杀冲动：你是否认为生存没有价值或生不如死？"
        };

        AnswerAdapter(Context context, int resource) {
            super(context, resource);
            resId = resource;
            Log.i(TAG, "create Adapter..");
        }

        /**
         * 定义ListView的长度，不写这个，没有数据出来\
         * @return 长度
         */
        @Override
        public int getCount() {
            return array.length;
        }

        /**
         * ListView的每一项的数据\
         * @param position 位置
         * @return 数据
         */
        @Nullable
        @Override
        public String getItem(int position) {
            if (position >= 0 && position < array.length) {
                return array[position];
            }
            return "";
        }

        /**
         * ListView的每一项创建的时候调用这个
         * @param position    位置
         * @param convertView 复用View
         * @param parent      父母View
         * @return 显示的View
         */
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            String answer = getItem(position);
            ViewHolder viewHolder;
            Log.i(TAG, "getView.. position: " + position + ", answer: " + answer);

            convertView = mInflater.inflate(resId, null);
            viewHolder = new ViewHolder(convertView).setPosition(position); //TODO => 在初始化ViewHold对象的时候，将位置信息传递进去，不然怎么知道点击的是哪一项的答案
            viewHolder.checked(mArrayMap.get(position, -1));    //TODO => 让选中的在翻滚后继续选中，不然一开始选中的对象，滑动回来，就没选中了
            viewHolder.title.setText(answer);   //TODO => 设置每一行的标题
            return convertView;
        }

        class ViewHolder {
            int position;
            @BindView(R.id.title) TextView title;
            @BindView(R.id.zero_score) RadioButton zeroScore;
            @BindView(R.id.two_score) RadioButton twoScore;
            @BindView(R.id.four_score) RadioButton fourScore;
            @BindView(R.id.six_score) RadioButton sixScore;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);   //TODO -> 这一句的作用是帮助我们findViewById和setOnClickListener
            }

            /**
             * ListView在翻滚后会清空数据，
             * 该方法会让ListView再选中对应的数据
             * @param score 分数，根据分数来选中对应的项
             */
            void checked(int score) {
                switch (score) {
                    case 0:
                        zeroScore.setChecked(true);
                        break;
                    case 1:
                        twoScore.setChecked(true);
                        break;
                    case 2:
                        fourScore.setChecked(true);
                        break;
                    case 3:
                        sixScore.setChecked(true);
                        break;
                }
            }

            /**
             * 设置Position，该position至关重要，没有position,就不知道是点击的哪一项的答案
             * @param p 位置
             * @return 返回this，可以链式编程
             */
            ViewHolder setPosition(int p) {
                position = p;
                return this;    //TODO -> 这样子写可以写链式函数
            }

            @OnCheckedChanged({R.id.zero_score, R.id.two_score, R.id.four_score, R.id.six_score})
            void onViewClicked(CompoundButton button, boolean checked) {
                Log.i(TAG, "OnCheckedChanged.. id: " + button.getId() + ", checked: " + checked);
                switch (button.getId()) {
                    case R.id.zero_score:
                        if (checked) {
                            mArrayMap.put(position, 0); //选中了第一项，得0分，将值保存在mArrayMap中
                        }
                        break;
                    case R.id.two_score:
                        if (checked) {
                            mArrayMap.put(position, 1); //选中了第一项，得1分，将值保存在mArrayMap中
                        }
                        break;
                    case R.id.four_score:
                        if (checked) {
                            mArrayMap.put(position, 2); //选中了第一项，得2分，将值保存在mArrayMap中
                        }
                        break;
                    case R.id.six_score:
                        if (checked) {
                            mArrayMap.put(position, 3); //选中了第一项，得3分，将值保存在mArrayMap中
                        }
                        break;
                }
            }
        }
    }
}
