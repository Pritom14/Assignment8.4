package com.example.shaloin.eightassignmentd;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayList<String> al_contactname,al_contactno,al_dob;
    private EditText ed_diag_name,ed_diag_number,ed_diag_dob;
    private MyAdapter adapter;
    String diag_name,diag_number,diag_dob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=(ListView)findViewById(R.id.listViewID);
        al_contactname=new ArrayList<>();
        al_contactno=new ArrayList<>();
        al_dob=new ArrayList<>();
        adapter=new MyAdapter();
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add: {
                Toast.makeText(MainActivity.this, "New Item Added", Toast.LENGTH_LONG).show();
                addItem();
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    public void addItem(){
        AlertDialog.Builder alertDialog=new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater=this.getLayoutInflater();
        alertDialog.setView(inflater.inflate(R.layout.dialog_contact,null))
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Dialog diag=(Dialog)dialogInterface;
                        ed_diag_name=(EditText)((Dialog) dialogInterface).findViewById(R.id.contactName);
                        ed_diag_number=(EditText)((Dialog) dialogInterface).findViewById(R.id.phoneNumber);
                        ed_diag_dob=(EditText)((Dialog) dialogInterface).findViewById(R.id.dateOfBirth);
                        diag_name=ed_diag_name.getText().toString();
                        diag_number=ed_diag_number.getText().toString();
                        diag_dob=ed_diag_dob.getText().toString();

                        al_contactname.add(diag_name);
                        al_contactno.add("+"+diag_number);
                        al_dob.add(diag_dob);
                        adapter.notifyDataSetChanged();
                        addItem();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        alertDialog.show();
    }

    public class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return al_contactname.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup) {
            ViewHolder holder;
            if(convertView==null){
                convertView=getLayoutInflater().inflate(R.layout.name_and_contacts,viewGroup,false);
                holder=new ViewHolder();
                holder.bindView(convertView);
                convertView.setTag(holder);
            }
            else{
                holder=(ViewHolder)convertView.getTag();
            }
            holder.contactName.setText(al_contactname.get(position));
            holder.contactNumber.setText(al_contactno.get(position));
            holder.contactDOB.setText(al_dob.get(position));
            return convertView;
        }
    }

    public class ViewHolder{
        TextView contactName,contactNumber,contactDOB;
        void bindView(View convertView){
            contactName=(TextView)convertView.findViewById(R.id.nameTextID);
            contactNumber=(TextView)convertView.findViewById(R.id.phoneTextID);
            contactDOB=(TextView)convertView.findViewById(R.id.dobTextID);
        }
    }
}
