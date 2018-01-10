package com.admin.projectadmintor.homePage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.admin.projectadmintor.R;
import com.admin.projectadmintor.bean.Picture;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/12/13.
 */

//自定义表格布局的适配器
public class PictureAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<Picture> pictures;     //向适配器里面添加数据

    public PictureAdapter(String[] titles, int[] images, Context context) {
        super();
        pictures = new ArrayList<Picture>();
        inflater = LayoutInflater.from(context);
        for (int i = 0; i < images.length; i++) {  //执行循环的方法向里面添加数据
            Picture picture = new Picture(null, images[i]);
            pictures.add(picture);
        }

    }

    @Override
    public int getCount() {
        if (null != pictures) {
            return pictures.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        return pictures.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.home_item_layout, null);
            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) convertView.findViewById(R.id.home_gv_tv);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.home_gv_iv);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.title.setText(pictures.get(position).getTitle());
        viewHolder.image.setImageResource(pictures.get(position).getImageId());
        return convertView;
    }
}
