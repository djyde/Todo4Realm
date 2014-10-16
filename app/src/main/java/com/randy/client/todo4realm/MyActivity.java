package com.randy.client.todo4realm;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.randy.client.todo4realm.adapter.TodoAdapter;
import com.randy.client.todo4realm.model.Todo;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmQuery;
import io.realm.RealmResults;


public class MyActivity extends Activity {

    private ListView lv;
    private ImageView add;
    private EditText title;
    private Realm realm;
    private TodoAdapter todoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        //Realm.deleteRealmFile(this);
        realm = Realm.getInstance(this);

        add = (ImageView)findViewById(R.id.add);
        title = (EditText)findViewById(R.id.title);
        lv = (ListView)findViewById(R.id.todo_list);

        RealmQuery<Todo> query = realm.where(Todo.class);
        RealmResults<Todo> results = query.findAll();


        todoAdapter = new TodoAdapter(results,this);
        lv.setAdapter(todoAdapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //添加一条todo
                realm.beginTransaction();

                Todo todo = realm.createObject(Todo.class);
                if(!title.getText().toString().equals("")){
                    todo.setTitle(title.getText().toString());
                    todo.setDone(false);
                }

                realm.commitTransaction();

                title.setText("");

                //监听数据库变化，更新ListView
                realm.addChangeListener(new RealmChangeListener() {
                    @Override
                    public void onChange() {
                        todoAdapter.notifyDataSetChanged();
                    }
                });

            }
        });


    }

}
