package in.samarthdigital.studentinfo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.DataViewHolder> {
    List<String> id, enroll, fname, lname, department;
    Context context;
    DataBaseHelper dataBaseHelper;

    public RecyclerAdapter(List<String> id, List<String> enroll, List<String> fname, List<String> lname, List<String> department, Context context) {
        this.id = id;
        this.enroll = enroll;
        this.fname = fname;
        this.lname = lname;
        this.department = department;
        this.context = context;
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_student, parent, false);
        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, final int position) {
        holder.no.setText(String.valueOf(position + 1) + ".");
        holder.name.setText(fname.get(position) + " " + lname.get(position));
        holder.enroll.setText(enroll.get(position));
        holder.dept.setText(department.get(position));
        setBack(holder, department.get(position));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //This method deletes the student from database
                showDialog(String.valueOf(fname.get(position) + " " + lname.get(position)), enroll.get(position),position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return enroll.size();
    }

    public class DataViewHolder extends RecyclerView.ViewHolder {
        TextView no, name, enroll, dept;
        CardView cardView;

        public DataViewHolder(View itemView) {
            super(itemView);
            this.no = itemView.findViewById(R.id.list_txt_no);
            this.name = itemView.findViewById(R.id.list_txt_name);
            this.enroll = itemView.findViewById(R.id.list_txt_enroll);
            this.dept = itemView.findViewById(R.id.list_txt_dept);
            this.cardView = itemView.findViewById(R.id.list_cardview);
        }
    }

    //This method set's Background to Cardview
    public void setBack(DataViewHolder holder, String dept) {
        switch (dept) {
            case "Computer Engineering":
                holder.cardView.setBackgroundResource(R.drawable.recycle_comp);
                break;
            case "Mechanical Engineering":
                holder.cardView.setBackgroundResource(R.drawable.recycle_mech);
                break;
            case "Electrical Engineering":
                holder.cardView.setBackgroundResource(R.drawable.recycle_elec);
                break;
            case "E&TC Engineering":
                holder.cardView.setBackgroundResource(R.drawable.recycle_etc);
                break;
        }

    }

    //This method Shows the Dialog to User
    public void showDialog(final String name, final String en, final int positon) {

        AlertDialog.Builder alert = new AlertDialog.Builder(context);

        alert.setMessage("Are You Sure to Delete " + name);

        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                dataBaseHelper = new DataBaseHelper(context);

                if (dataBaseHelper.deleteData(en) != 0) {

                    Toast.makeText(context, name + " is deleted!", Toast.LENGTH_SHORT).show();
                    id.remove(positon);
                    enroll.remove(positon);
                    fname.remove(positon);
                    lname.remove(positon);
                    department.remove(positon);
                    notifyDataSetChanged();
                }
            }
        });

        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog = alert.create();
        alertDialog.show();
    }

}
