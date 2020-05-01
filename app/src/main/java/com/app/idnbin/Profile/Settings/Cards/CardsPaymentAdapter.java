package com.app.idnbin.Profile.Settings.Cards;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.idnbin.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CardsPaymentAdapter extends RecyclerView.Adapter<CardsPaymentAdapter.CardsViewHolder> {
    ArrayList<CardsData> cardsArrayList;
    Context context;
    int selectedPosition=0;
    private static final String TAG = "CardsRecyclerViewAd";

    public CardsPaymentAdapter(Context context, ArrayList<CardsData> cardsArrayList) {
        this.context = context;
        this.cardsArrayList = cardsArrayList;
    }


    @NonNull
    @Override
    public CardsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cards_payment, parent, false);
        return new CardsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardsViewHolder holder, int position) {

        holder.radio_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(cardsArrayList.get(position).isChecked())
                {
                    cardsArrayList.get(position).setChecked(false);
                }
                else {
                    cardsArrayList.get(position).setChecked(true);
                }
                notifyDataSetChanged();
            }
        });

        if(cardsArrayList.get(position).isChecked())
        {
            holder.radio_button.setChecked(true);
            holder.imgDelete.setVisibility(View.VISIBLE);
        }
        else {
            holder.radio_button.setChecked(false);
            holder.imgDelete.setVisibility(View.GONE);
        }

        holder.TV_cardname.setText(cardsArrayList.get(position).getCardName());
        holder.TV_cardnumber.setText(cardsArrayList.get(position).getCardNumber());

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardsArrayList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,cardsArrayList.size());
            }
        });

    }

    @Override
    public int getItemCount() {
        return cardsArrayList.size();
    }


    public class CardsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.radio_button)
        RadioButton radio_button;
        @BindView(R.id.imgDelete)
        ImageView imgDelete;
        @BindView(R.id.TV_cardnumber)
                TextView TV_cardnumber;
        @BindView(R.id.TV_cardname)
                TextView TV_cardname;
        View view;

        public CardsViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, itemView);

        }
    }
}
