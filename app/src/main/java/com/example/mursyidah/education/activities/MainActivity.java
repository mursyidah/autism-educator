package com.example.mursyidah.education.activities;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mursyidah.education.R;
import com.example.mursyidah.education.model.MyData;
import com.example.mursyidah.education.sql.DatabaseHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper mDatabaseHelper;
    public ListView listView;
    ArrayList<MyData> listData = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.myList);
        mDatabaseHelper = new DatabaseHelper(this);
        CustomAdapter adapter = new CustomAdapter(this, R.layout.custom_activity, listData);
        listView.setAdapter(adapter);

        Cursor data = mDatabaseHelper.getdata();
        if (data.moveToFirst()) {
            do {
                String name = data.getString(1);
                String picturePath = data.getString(data.getColumnIndex("image"));

                listData.add(new MyData(name, picturePath));
            } while (data.moveToNext());
        }
    }


    public class CustomAdapter extends BaseAdapter
    {
        private Context context;private int layout;
        ArrayList<MyData> textList;
        public CustomAdapter(Context context, int layout, ArrayList<MyData> textList)
        {
            this.context = context;
            this.layout = layout;
            this.textList = textList;
        }
        @Override
        public int getCount()
        {
            return textList.size();
        }

        @Override
        public Object getItem(int position) {
            return textList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        private class ViewHolder
        {
            ImageView imageView1;
            TextView textName;
        }
        @Override
        public View getView(int position, View view, ViewGroup parent)
        {
            View row = view;

            ViewHolder holder;

            if (row == null
                    ) {

                LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
                row = inflater.inflate(layout, null);
                holder= new ViewHolder();
                row.setTag(holder);

            }
            else
            {
                holder =(ViewHolder) row.getTag();

            }
            final MyData pic = textList.get(position);
            holder.textName.setText(pic.getName());
            String picImage = pic.getPath();
//            final Bitmap bitmap = BitmapFactory.decodeByteArray(picImage, 0 , picImage.length());
//            holder.imageView1.setImageBitmap(bitmap);
            holder.imageView1.setImageURI(Uri.parse(picImage));

            row.setOnClickListener(new AdapterView.OnClickListener() {

                @Override
                public void onClick(View v)
                {
                    Cursor data = mDatabaseHelper.getItemId(pic.getName());
                    int itemId = -1;
                    String FruitName = "";
                    String FruitImage = null;

                    while(data.moveToNext())
                    {
                        itemId = data.getInt(0);
                        FruitName = data.getString(1);
                        FruitImage = data.getString(2);


                    }
                    if(itemId > -1)
                    {
                        ToastMessage("On item Click: the id is" + itemId + " " + FruitName + " " + FruitImage);
                        Intent editScreenIntent = new Intent(MainActivity.this, DisplayData.class);

                        editScreenIntent.putExtra("id",itemId);
                        editScreenIntent.putExtra("name", FruitName);
                        editScreenIntent.putExtra("image", FruitImage);


                        startActivity(editScreenIntent);
                    }

                    else
                    {
                        ToastMessage("No Data");

                    }

                }


            });
            return row;
        }
    }

    private void ToastMessage(String s)
    {
        Toast.makeText(this, s ,Toast.LENGTH_SHORT).show();
    }
}
