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

import com.stfalcon.frescoimageviewer.ImageViewer;
import com.yarolegovich.lovelydialog.LovelyStandardDialog;

import java.util.ArrayList;
import java.util.List;

import bk.itc.html5.mylib.component.view.gridnavigation.GridNavigation;
import bk.itc.html5.mylib.component.view.recycler.MyRecyclerAdapter;
import bk.itc.html5.mylib.component.view.recycler.MyRecyclerView;
import bk.itc.html5.mylib.component.view.recycler.MyViewHolder;
import bk.itc.html5.mylib.component.view.setting.SettingView;
import bk.itc.html5.mylib.component.view.topicview.TopicView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TopicView topicView = (TopicView) findViewById(R.id.my_topic_view);
        topicView.config(Color.BLACK, Color.WHITE, 3);
        topicView.configStroke(2, Color.BLUE);
        topicView.addItem("English Listening", R.drawable.english, null);
        topicView.addItem("Study English", R.drawable.study, new TopicView.TopicListener() {
            @Override
            public void onTopicClick() {
                Toast.makeText(MainActivity.this, "Fuck ", Toast.LENGTH_SHORT).show();
            }
        });
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
}
