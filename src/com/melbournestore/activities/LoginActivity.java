package com.melbournestore.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.melbournestore.application.SysApplication;

public class LoginActivity extends Activity{
	
	private TextView loginText;
	private EditText loginNumber;
	private CheckBox loginCheckbox;
	private TextView loginTextAgreement;
	private Button loginButton;
	
	
	
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_layout);
		
		SysApplication.getInstance().addActivity(this); 
		
		// Set up action bar.
		final ActionBar actionBar = getActionBar();

		// Specify that the Home button should show an "Up" caret, indicating
		// that touching the
		// button will take the user one step up in the application's hierarchy.
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		loginText = (TextView) findViewById(R.id.login_notice);
		loginText.setText("��ʹ���ֻ������¼");

		loginNumber = (EditText) findViewById(R.id.login_number);
		loginNumber.setHint("�Ĵ�����10λ����");
		
		loginCheckbox = (CheckBox) findViewById(R.id.login_aggrement_checkbox);
		loginCheckbox.setText("");
		
		loginTextAgreement = (TextView) findViewById(R.id.login_text_agreement);
		loginTextAgreement.setText("ͬ��<ī�����Ͳͷ���Э��>");
		
		loginTextAgreement.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(LoginActivity.this, DeliveryAgreementActivity.class);
				startActivity(intent);
			}
			
		});
		
		loginButton = (Button) findViewById(R.id.login_button);
		loginButton.setText("��¼");
		
		loginButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
				if(!loginCheckbox.isChecked()){
					new AlertDialog.Builder(LoginActivity.this)  
			        .setMessage("��ͬ��ī�����Ͳͷ���Э��")   
			        .setPositiveButton("ȷ��",   
			        new DialogInterface.OnClickListener(){  
			                  public void onClick(DialogInterface dialoginterface, int i){   
			                                 //��ť�¼�   
			                            Toast.makeText(LoginActivity.this, "ȷ��",Toast.LENGTH_LONG).show();  
			                              }   
			                      }).show();  
				}
				else if((loginNumber.getText().length()!=10)||!loginNumber.getText().toString().subSequence(0, 2).equals("04")){
					new AlertDialog.Builder(LoginActivity.this)  
			        .setMessage("�ֻ����벻�ǰ����ֻ�\n������04��ͷ����")   
			        .setPositiveButton("ȷ��",   
			        new DialogInterface.OnClickListener(){  
			                  public void onClick(DialogInterface dialoginterface, int i){   
			                                 //��ť�¼�   
			                            Toast.makeText(LoginActivity.this, "ȷ��",Toast.LENGTH_LONG).show();  
			                              }   
			                      }).show(); 
				}
				else{
					Intent returnIntent = new Intent();
					returnIntent.putExtra("number",loginNumber.getText().toString());
					setResult(RESULT_OK,returnIntent);
					finish();

				}
			}
			
		});
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This is called when the Home (Up) button is pressed in the action
			// bar.
			// Create a simple intent that starts the hierarchical parent
			// activity and
			// use NavUtils in the Support Package to ensure proper handling of
			// Up.
			Intent upIntent = NavUtils.getParentActivityIntent(this);
			upIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
			startActivity(upIntent);
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
    private long mExitTime;
    public boolean onKeyDown(int keyCode, KeyEvent event) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                                    Toast.makeText(this, "�ٰ�һ���˳�����", Toast.LENGTH_SHORT).show();
                                    mExitTime = System.currentTimeMillis();

                            } else {
                            		SysApplication.getInstance().exit();  
                            }
                            return true;
                    }
                    return super.onKeyDown(keyCode, event);
            }
}