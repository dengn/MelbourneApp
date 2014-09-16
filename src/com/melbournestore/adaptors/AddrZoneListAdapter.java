package com.melbournestore.adaptors;

import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.melbournestore.activities.R;
import com.melbournestore.models.ItemEntity;

public class AddrZoneListAdapter extends BaseAdapter {
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    private Context mContext;
    private List<ItemEntity> mData;
    private LayoutInflater mLayoutInflater;
    
    private Handler mHandler;
    
    // ===========================================================
    // Constructors
    // ===========================================================

    public AddrZoneListAdapter(Context pContext, List<ItemEntity> pData, Handler handler) {
        mContext = pContext;
        mData = pData;
        
        mLayoutInflater = LayoutInflater.from(mContext);
        
        mHandler = handler;
    }
    
    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 常见的优化ViewHolder
        ViewHolder viewHolder = null;
        if (null == convertView) {
            convertView = mLayoutInflater.inflate(R.layout.suburb_list_item, null);
            
            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) convertView.findViewById(R.id.suburb_title); 
            viewHolder.content = (TextView) convertView.findViewById(R.id.suburb_content);

            
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // 获取数据
        final ItemEntity itemEntity = (ItemEntity) getItem(position);
        viewHolder.content.setText(itemEntity.getContent());
        
        viewHolder.content.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Message message = new Message();
				Bundle b = new Bundle();
				// send the suburb chosen
				b.putString("suburb", itemEntity.getContent());
				message.setData(b);
				message.what = 1;
				mHandler.sendMessage(message);
			}
        	
        });

        if ( needTitle(position) ) {
            // 显示标题并设置内容 
            viewHolder.title.setText(itemEntity.getTitle());
            viewHolder.title.setVisibility(View.VISIBLE);
        } else {
            // 内容项隐藏标题
            viewHolder.title.setVisibility(View.GONE);
        }
        
        return convertView;
    }
    
    @Override
    public int getCount() {
        if (null != mData) {
            return mData.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (null != mData && position < getCount()) {
            return mData.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    
    // ===========================================================
    // Methods
    // ===========================================================

    /**
     * 判断是否需要显示标题
     * 
     * @param position
     * @return
     */
    private boolean needTitle(int position) {
        // 第一个肯定是分类
        if (position == 0) {
            return true;
        }
        
        // 边界处理
        if (position < 0) {
            return false;
        }
         
        // 当前  // 上一个
        ItemEntity currentEntity = (ItemEntity) getItem(position);
        ItemEntity previousEntity = (ItemEntity) getItem(position - 1);
        if (null == currentEntity || null == previousEntity) {
            return false;
        }
        
        String currentTitle = currentEntity.getTitle();
        String previousTitle = previousEntity.getTitle();
        if (null == previousTitle || null == currentTitle) {
            return false;
        }
        
        // 当前item分类名和上一个item分类名不同，则表示两item属于不同分类
        if (currentTitle.equals(previousTitle)) {
            return false;
        }
        
        return true;
    }
    
    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================
    
    private class ViewHolder {
        TextView title;
        TextView content;
    }
    
}

