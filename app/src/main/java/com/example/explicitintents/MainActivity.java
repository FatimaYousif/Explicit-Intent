package com.example.explicitintents;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button call;
    Button camera;
    Button contact;
    Button browser;
    Button call_log;
    Button gallery;
    Button dial_pad;
    EditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize buttons
        call=(Button)findViewById(R.id.call);
        camera=(Button)findViewById(R.id.camera);
        contact=(Button)findViewById(R.id.contact);
        browser=(Button)findViewById(R.id.browser);
        call_log=(Button)findViewById(R.id.call_log);
        gallery=(Button)findViewById(R.id.gallery);
        dial_pad=(Button)findViewById(R.id.dial_pad);
        input=(EditText)findViewById(R.id.input);


        //for call button
        call.setOnClickListener(new View.OnClickListener(){


            private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1 ;  //any value

            @Override
                                    public void onClick(View v)
                                    {
                                     String number = ("tel:" + input.getText());
                                     Intent i = new Intent(Intent.ACTION_CALL);
                                     i.setData(Uri.parse(number));
                                     if (ContextCompat.checkSelfPermission
                                             (MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
                                     {
                                         ActivityCompat.requestPermissions(MainActivity.this,
                                                 new String[]{Manifest.permission.CALL_PHONE}, MY_PERMISSIONS_REQUEST_CALL_PHONE);
                                     }
                                     else {
                                         try { startActivity(i); }
                                         catch(SecurityException e)
                                         { e.printStackTrace();
                                         }
                                     }

                                    }
                                }
        );



        //for camera btn
        camera.setOnClickListener(new View.OnClickListener(){
                                      @Override
                                      public void onClick(View v)
                                      {
                                          Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                          startActivity(i);
                                      }
                                  }
        );

        //contact btn
        contact.setOnClickListener(new View.OnClickListener(){
                                       @Override
                                       public void onClick(View v)
                                       {
                                           Uri uri = ContactsContract.Contacts.CONTENT_URI;
                                           Intent i = new Intent(Intent.ACTION_PICK,uri);
                                           startActivity(i);
                                       }
                                   }
        );

        //browser
        browser.setOnClickListener(new View.OnClickListener(){
                                       @Override
                                       public void onClick(View v)
                                       {
                                           String inp=input.getText().toString();
                                           Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse(inp));
                                           startActivity(i);
                                       }
                                   }
        );

        //call log
        call_log.setOnClickListener(new View.OnClickListener(){
                                        @Override
                                        public void onClick(View v)
                                        {
                                            Intent showCallLog = new Intent();
                                            showCallLog.setAction(Intent.ACTION_VIEW);
                                            showCallLog.setType(CallLog.Calls.CONTENT_TYPE);
                                            startActivity(showCallLog);
                                        }
                                    }
        );

        //gallery
        gallery.setOnClickListener(new View.OnClickListener(){
                                       @Override
                                       public void onClick(View v)
                                       {
                                           Intent intent = new Intent();
                                           intent.setAction(android.content.Intent.ACTION_VIEW);
                                           intent.setType("image/*");
                                           intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                           startActivity(intent);
                                       }
                                   }
        );

        //dial pad
        dial_pad.setOnClickListener(new View.OnClickListener(){
                                        @Override
                                        public void onClick(View v)
                                        {
                                            String inp=input.getText().toString();
                                            String number = ("tel:" + inp);
                                            Intent intent = new Intent(Intent.ACTION_DIAL,Uri.parse(number));
                                            startActivity(intent);
                                        }
                                    }
        );

    }
}