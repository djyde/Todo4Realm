package com.randy.client.todo4realm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.randy.client.todo4realm.R;
import com.randy.client.todo4realm.model.Todo;

import java.util.HashMap;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by randy on 14-10-12.
 */
public class TodoAdapter extends BaseAdapter {
    Context context;
    RealmResults<Todo> todoList;
    public TodoAdapter(RealmResults<Todo> todoList,Context context){
        this.todoList = todoList;
        this.context = context;
    }

    public class ViewHolder{
        CheckBox checkBox;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getCount() {
        return todoList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.todo_item,null);
            holder = new ViewHolder();
            holder.checkBox = (CheckBox)view.findViewById(R.id.done);
            view.setTag(holder);
        } else {
            holder = (ViewHolder)view.getTag();
        }

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Realm realm = Realm.getInstance(context);
                realm.beginTransaction();
                todoList.get(i).setDone(b);
                realm.commitTransaction();
            }
        });

        holder.checkBox.setChecked(todoList.get(i).isDone());
        holder.checkBox.setText(todoList.get(i).getTitle());
        return view;
    }
}
