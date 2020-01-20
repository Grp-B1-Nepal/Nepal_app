package com.example.nepal_app.Logic.Adaptor;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nepal_app.R;

import java.util.ArrayList;

public class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.ViewHolder>{

    int speakerimage;
    ArrayList<String> informationtext;
    public MediaPlayer mediaPlayer= new MediaPlayer();
    public ArrayList<Integer> sounds;
    private Boolean isSoundPlaying = false;
    private Context mcontext;

    public ActivityAdapter(int speakerimage, ArrayList<String> informationtext, ArrayList<Integer> sounds) {
//This information is passed on by the overlaying fragment containing the recycler view.
        this.speakerimage = speakerimage;
        this.informationtext = informationtext;
        this.sounds = sounds;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item, parent, false);
        ViewHolder vh = new ViewHolder(view);
        //Context is needed by the media player a couple of methods down.
        mcontext = parent.getContext();

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.layout_speakerimage.setImageResource(speakerimage);
        //The image ressource is passed on by the activity, such that we know what image it is. This can probably be found in a different way tho.
        //Got confused because i couldn't find any nice way to do it.
        //TODO improve the above line.

        holder.layout_informationtext.setText(informationtext.get(position));

    }

    @Override
    public int getItemCount() {
        return informationtext.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements OnClickListener{

        public ImageView layout_speakerimage;
        public TextView layout_informationtext;


        public ViewHolder(View itemView) {
            super(itemView);
            layout_speakerimage = itemView.findViewById(R.id.activityitem_imgview);
            layout_informationtext = itemView.findViewById(R.id.activityitem_txtview);

            layout_speakerimage.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
                if(!isSoundPlaying) {
                    //TODO when more sound files are added it has to pick between them. Right now it's also only the speaker talking.
                    isSoundPlaying = true;
                    mediaPlayer = MediaPlayer.create(mcontext, sounds.get(getLayoutPosition()));
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            mediaPlayer.release();
                            isSoundPlaying = false;
                        }
                    });
                }
        }
    }
}



