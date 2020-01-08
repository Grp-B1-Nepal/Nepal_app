package com.example.nepal_app.Adaptor;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nepal_app.R;

import java.util.ArrayList;
import java.util.List;

public class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.ViewHolder>{

    int speakerimage;
    ArrayList<String> informationtext;
    private OnNoteListener mOnNoteListener;
    private static OnClickListener mOnClickListener;
    public MediaPlayer mediaPlayer= new MediaPlayer();
    public ArrayList<Integer> sounds;
    private Context mcontext;


    public ActivityAdapter(int speakerimage, ArrayList<String> informationtext, OnNoteListener onNoteListener, ArrayList<Integer> sounds) {

        this.speakerimage = speakerimage;
        this.informationtext = informationtext;
        this.mOnNoteListener = onNoteListener;
        this.sounds = sounds;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item, parent, false);
        ViewHolder vh = new ViewHolder(view, mOnNoteListener);
        mcontext = parent.getContext();

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.layout_speakerimage.setImageResource(speakerimage);
        //texts

        holder.layout_informationtext.setText(informationtext.get(position));

    }

    @Override
    public int getItemCount() {
        return informationtext.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements OnClickListener{

        public ImageView layout_speakerimage;
        public TextView layout_informationtext;
        OnNoteListener onNoteListener;


        public ViewHolder(View itemView, OnNoteListener OnNoteListner) {
            super(itemView);
            layout_speakerimage = itemView.findViewById(R.id.activityitem_imgview);
            layout_informationtext = itemView.findViewById(R.id.activityitem_txtview);

            this.onNoteListener = mOnNoteListener;
            layout_speakerimage.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(), "position = " + getLayoutPosition(), Toast.LENGTH_SHORT).show();
            if (getLayoutPosition() == 0 ) {

                mediaPlayer = MediaPlayer.create(mcontext, sounds.get(0));
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mediaPlayer.release();
                    }
                });
            }
        }
    }

    public interface OnNoteListener{
        void onNoteClick(int position);

    }
}



