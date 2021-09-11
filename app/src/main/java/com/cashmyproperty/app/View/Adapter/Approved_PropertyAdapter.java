package com.cashmyproperty.app.View.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cashmyproperty.app.R;
import com.cashmyproperty.app.View.DetailPage.DetailsActivity;
import com.cashmyproperty.app.View.Response.Image__1;
import com.cashmyproperty.app.View.Response.RestPropertyDatum;

import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static android.media.CamcorderProfile.get;
import static android.media.MediaExtractor.MetricsConstants.FORMAT;

public class Approved_PropertyAdapter extends RecyclerView.Adapter<Approved_PropertyAdapter.ViewHolder> {

    Context context;
    private static final String FORMAT = "%02d:%02d:%02d:%02d";
    private static final SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
    private SimpleDateFormat dateFormat;
    List<RestPropertyDatum> restPropertyData;
    String image_base_url = "https://apkconnectlab.com/cmpdtest/";


    public Approved_PropertyAdapter(Context context,  List<RestPropertyDatum> restPropertyData) {
        this.context = context;
        this.restPropertyData = restPropertyData;
    }


    @Override
    public Approved_PropertyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_home, parent, false);
        return new Approved_PropertyAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Approved_PropertyAdapter.ViewHolder holder, int position) {


        String address=  restPropertyData.get(position).getAddress();
        String auction_end_date= restPropertyData.get(position).getExactDate();

        Long ff=(new Date(auction_end_date)).getTime();/*"2021/09/18"*/
       // Toast.makeText(context,String.valueOf(ff),Toast.LENGTH_SHORT).show();

        Calendar calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String date = dateFormat.format(calendar.getTime());

       // System.out.println();

        Long current_time = (new Date(date)).getTime();
         // Long current_time = System.currentTimeMillis()/1000;
          Long time  = ff - current_time;
       // Toast.makeText(context,String.valueOf(current_time),Toast.LENGTH_SHORT).show();


        //Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        //cal.setTimeInMillis(auction_end_date * 1000);
        //String date = DateFormat.format("dd-MM-yyyy", cal).toString();
        //Toast.makeText(context,date,Toast.LENGTH_SHORT).show();

        holder.name.setText(restPropertyData.get(position).getPropertyName());
        holder.bid.setText("Last Bid Amount- "+String.valueOf(restPropertyData.get(position).getLastBid()));
       // String upperString = address.substring(0, 1).toUpperCase() + address.substring(1).toLowerCase();
        holder.locat.setText(address);
        holder.prop_id.setText(" Property ID # "+restPropertyData.get(position).getPropertySequenceId());


            new CountDownTimer(time, 1000) {
                public void onTick(long millisUntilFinished) {


                    long Days = millisUntilFinished / (24 * 60 * 60 * 1000);
                    long Hours = millisUntilFinished / (60 * 60 * 1000) % 24;
                    long Minutes = millisUntilFinished / (60 * 1000) % 60;
                    long Seconds = millisUntilFinished / 1000 % 60;
                    holder.time_set.setText("Time Left-"+String.format("%02d days ",Days)+String.format("%02d hrs ",Hours)
                            +String.format("%02d min ",Minutes)+String.format("%02dsec ",Seconds));

                    /*long millis = millisUntilFinished;
                    String hms = String.format("%02d:%02d:%02d:%02d",
                            TimeUnit.HOURS.toDays(TimeUnit.MILLISECONDS.toHours(millis)),
                            (TimeUnit.MILLISECONDS.toHours(millis) -
                                    TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(millis))),
                            (TimeUnit.MILLISECONDS.toMinutes(millis) -
                                    TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis))), (TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis))));
                    holder.time_set.setText(hms);//set text*/

                    /*holder.time_set.setText(""+String.format(FORMAT,
                            TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                                    TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                            TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));*/
                }


               /* long days=millisUntilFinished/86400;
                    long hrs=days/
                    long seconds = millisUntilFinished/1000;
                    long minutes = seconds / 60;
                    long hours = minutes / 60;
                    if(minutes > 0)
                        seconds = seconds % 60;
                    if(hours > 0)
                        minutes = minutes % 60;
                    String time = formatNumber(hours) + ":" + formatNumber(minutes) + ":" +
                            formatNumber(seconds);
                    holder.time_set.setText("Time Left- "+time);*/

                public void onFinish() {
                    Toast.makeText(context,"timing issue",Toast.LENGTH_SHORT).show();
                }
            }.start();


        Glide.with(context).load(image_base_url+restPropertyData.get(position).getImages().get(0).getPropertyImage()).into(holder.cat_img);
        holder.view_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, DetailsActivity.class);
                intent.putExtra("propertyid",String.valueOf(restPropertyData.get(position).getPropertyId()));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, DetailsActivity.class);
                intent.putExtra("propertyid",String.valueOf(restPropertyData.get(position).getPropertyId()));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return restPropertyData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView cat_img;
        Button view_details;
        RelativeLayout layout;
        TextView name,locat,bid,prop_id,time_set;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cat_img = itemView.findViewById(R.id.cat_img);
            view_details=itemView.findViewById(R.id.view_details);
            name=itemView.findViewById(R.id.name);
            layout=itemView.findViewById(R.id.layout);
            locat=itemView.findViewById(R.id.locat);
            bid=itemView.findViewById(R.id.bid);
            prop_id=itemView.findViewById(R.id.prop_id);
            time_set=itemView.findViewById(R.id.time_set);
        }
    }

   /* private String formatNumber(long value){
        if(value < 10)
            return "0" + value;
        return value + "";
    }*/
}

