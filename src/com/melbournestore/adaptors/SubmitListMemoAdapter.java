package com.melbournestore.adaptors;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnFocusChangeListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.melbournestore.activities.R;
import com.melbournestore.models.Order_user;

public class SubmitListMemoAdapter extends BaseAdapter{
	

	
	Handler mHandler;
	Context mContext;
	
	private String memo;

	Order_user mCurrentOrder;

	
	private static LayoutInflater inflater = null;
	
	public SubmitListMemoAdapter(Context context, Handler handler, Order_user currentOrder) {
		// TODO Auto-generated constructor stub

		
		mContext = context;
		mHandler = handler;
		mCurrentOrder = currentOrder;

		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	public void refresh(Order_user currentOrder){
		mCurrentOrder = currentOrder;
		notifyDataSetChanged();
	}


	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		viewHolder_edittext holder_edittext = null;

        holder_edittext = new viewHolder_edittext();
        convertView = inflater.inflate(R.layout.submit_list_item_memo, parent, false);
        	
        holder_edittext.title = (TextView)convertView.findViewById(R.id.memo_title);
        holder_edittext.memo = (EditText)convertView.findViewById(R.id.memo_info);
        	
        holder_edittext.title.setText("ƫ         ��");
        holder_edittext.memo.setText(mCurrentOrder.getRemark());
        holder_edittext.memo.setHint("�����뱸ע");
        
        holder_edittext.memo.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
            	
                if (!hasFocus){
                    final int position = v.getId();
                    final EditText Caption = (EditText) v;
                    memo = Caption.getText().toString();
                    
    				Message message = new Message();
    				Bundle b = new Bundle();
    				// send the position
    				b.putString("memo", memo);
    				message.setData(b);

    				// memo = 2
    				message.what = 2;

    				mHandler.sendMessage(message);
                }
            }
        });
        	
        convertView.setTag(holder_edittext);


		return convertView;
		
	}
	
	

    
    class viewHolder_edittext{

        private TextView title;

        private EditText memo;

    }

}
