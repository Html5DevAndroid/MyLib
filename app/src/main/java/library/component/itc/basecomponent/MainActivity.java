package library.component.itc.basecomponent;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import bk.itc.html5.mylib.component.data.MyFile;
import bk.itc.html5.mylib.component.util.DrawableUtil;
import bk.itc.html5.mylib.component.view.gridnavigation.GridNavigation;
import bk.itc.html5.mylib.component.view.quiz.QuizAnswerModel;
import bk.itc.html5.mylib.component.view.quiz.QuizModel;
import bk.itc.html5.mylib.component.view.quiz.QuizView;
import bk.itc.html5.mylib.component.view.recycler.MyRecyclerAdapter;
import bk.itc.html5.mylib.component.view.recycler.MyRecyclerView;
import bk.itc.html5.mylib.component.view.recycler.MyViewHolder;
import bk.itc.html5.mylib.component.view.setting.SettingView;
import bk.itc.html5.mylib.component.view.switcher.SwitcherView;
import bk.itc.html5.mylib.component.view.topicview.TopicView;
import bk.itc.html5.mylib.component.view.topicview2.TopicView2;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init6();
    }

    private void init1() {
        SettingView settingView = findViewById(R.id.setting_view);

        settingView.addSwitch("My First Function", "jav1", new SettingView.StateListener() {
            @Override
            public void onStateChangeByUser(boolean state) {
                Log.d("Fuck S", " " + state);
            }
        });

        settingView.addCheckBox("My Second Function", "jav2", new SettingView.StateListener() {
            @Override
            public void onStateChangeByUser(boolean state) {
                Log.d("Fuck C", " " + state);
            }
        });

        settingView.addButton("My Third Function", "Trade", new SettingView.ButtonListener() {
            @Override
            public void onButtonClick() {
                Toast.makeText(MainActivity.this, "I'm rich now", Toast.LENGTH_SHORT).show();
            }
        });

        settingView.addSlider("My Fourth Function", "jav3", 1, 50, 25, new SettingView.SliderListener() {
            @Override
            public void onSliderChange(int value) {

            }
        });

        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        settingView.addDropDown("My Fith Function", "jav4", list, 2, new SettingView.DropListener() {
            @Override
            public void onSelected(int index) {
                Log.d("Fuck ", "" + index);
            }
        });
    }

    private void init2() {
        final List<String> list = new ArrayList<>();
        list.add("Fuck 1");
        list.add("Fuck 2");
        list.add("Fuck 3");
        list.add("Fuck 4");
        list.add("Fuck 5");
        list.add("Fuck 6");

        MyRecyclerView myRecyclerView = (MyRecyclerView) findViewById(R.id.my_recycler_view);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        myRecyclerView.setAdapter(new MyRecyclerAdapter.MyRecyclerListener() {
            @Override
            public MyViewHolder onCreateView() {
                final Button button = new Button(MainActivity.this);
                button.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

                final MyViewHolder myViewHolder = new MyViewHolder(button);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int position = myViewHolder.getAdapterPosition();
                        button.setText(list.get(position) + " " + position);
                    }
                });

                return myViewHolder;
            }

            @Override
            public void onBind(int position, View view) {
                Button button = (Button) view;
                button.setText(list.get(position));
            }
        });
        myRecyclerView.setSize(list.size());
    }

    private void init3() {
        GridNavigation navigation = (GridNavigation) findViewById(R.id.my_grid);
        navigation.setBackground(DrawableUtil.createBackground(Color.WHITE, 5, 0, Color.WHITE));
        navigation.addItem("bitcoin dump", R.drawable.xx, new GridNavigation.GridListener() {
            @Override
            public void onClick() {

            }
        });

        navigation.addItem("bitcoin 4k", R.drawable.xx, new GridNavigation.GridListener() {
            @Override
            public void onClick() {
                Toast.makeText(MainActivity.this, "I did it", Toast.LENGTH_SHORT).show();
            }
        });

        navigation.addItem("bitcoin down", R.drawable.xx, null);
        navigation.addItem("bitcoin down", R.drawable.xx, null);
        navigation.addItem("bitcoin down sml now now now now", R.drawable.xx, null);
        navigation.addItem("bitcoin down", R.drawable.xx, null);
        navigation.addItem("bitcoin down", R.drawable.xx, null);
        navigation.addItem("bitcoin down", R.drawable.xx, null);
    }

    private void init4() {
        SwitcherView switcherView = (SwitcherView) findViewById(R.id.my_switcher_view);
        Button button = new Button(this);
        button.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        button.setText("Fuck 1");
        button.setBackgroundColor(Color.WHITE);
        switcherView.addView(button);

        Button button2 = new Button(this);
        button2.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        button2.setText("Fuck 2");
        button2.setBackgroundColor(Color.WHITE);
        switcherView.addView(button2);

        Button button3 = new Button(this);
        button3.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        button3.setText("Fuck 3");
        button3.setBackgroundColor(Color.GREEN);
        switcherView.addView(button3);

        Button button4 = new Button(this);
        button4.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        button4.setText("Fuck 4");
        button4.setBackgroundColor(Color.RED);
        switcherView.addView(button4);

        switcherView.replaceWithAnim(button3, SwitcherView.SLIDE_DIR_RIGHT_ADD, 1000, false);
    }

    private void init5() {
        TopicView topicView = (TopicView) findViewById(R.id.my_topic_view);
        topicView.addItem("Tiếng Anh 123", R.drawable.english, null);
        topicView.configText(Color.BLACK, 12);
        topicView.configTextAlign(TopicView.TEXT_LEFT);
    }

    private void init6() {
        QuizView quizView = (QuizView) findViewById(R.id.my_quiz_view);
        quizView.setMode(QuizView.MODE_SINGLE_CHOICE);

        quizView.read(MyFile.readFile(R.raw.quiz));
        quizView.goTo(0);

        quizView.setBackListener(new QuizView.BackListener() {
            @Override
            public void onBack() {
                System.exit(0);
            }
        });
    }

    private void init7() {
        TopicView2 topicView = (TopicView2) findViewById(R.id.my_topic_view_2);
        topicView.addItem("https://i.pinimg.com/originals/c2/4b/e8/c24be8b914079df7aad2e3fb267d40f7.jpg",
                "The first thing to do",
                "Em có 2 activity A và B,em startactivity từ A qua B, ở B em insert data và nhảy về lại A, vậy cho em hỏi làm sao để update lại data trong adpater ở Activity A.em sử dụng recycleview. ở B em có startActivityForResult nhưng không được. Em cảm ơn !" +
                        "Em có 2 activity A và B,em startactivity từ A qua B, ở B em insert data và nhảy về lại A, vậy cho em hỏi làm sao để update lại data trong adpater ở Activity A.em sử dụng recycleview. ở B em có startActivityForResult nhưng không được. Em cảm ơn !" +
                        "Em có 2 activity A và B,em startactivity từ A qua B, ở B em insert data và nhảy về lại A, vậy cho em hỏi làm sao để update lại data trong adpater ở Activity A.em sử dụng recycleview. ở B em có startActivityForResult nhưng không được. Em cảm ơn !"
                ,
                null);
        topicView.addItem("https://i.pinimg.com/originals/c2/4b/e8/c24be8b914079df7aad2e3fb267d40f7.jpg",
                "The first thing to do",
                "Em có 2 activity A và B,em startactivity từ A qua B, ở B em insert data và nhảy về lại A, vậy cho em hỏi làm sao để update lại data trong adpater ở Activity A.em sử dụng recycleview. ở B em có startActivityForResult nhưng không được. Em cảm ơn !",
                null);
    }
}
