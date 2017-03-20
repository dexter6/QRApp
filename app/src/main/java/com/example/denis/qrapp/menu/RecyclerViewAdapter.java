package com.example.denis.qrapp.menu;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.provider.ContactsContract;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.denis.qrapp.R;
import com.example.denis.qrapp.data.Kafic;
import com.example.denis.qrapp.data.NarucenoPice;
import com.example.denis.qrapp.data.Pice;
import com.example.denis.qrapp.root.App;
import com.example.denis.qrapp.root.ApplicationComponent;
import com.example.denis.qrapp.root.ApplicationModule;
import com.example.denis.qrapp.root.ApplicationModule_ProvideContextFactory;
import com.example.denis.qrapp.root.DaggerApplicationComponent;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import javax.annotation.Resource;
import javax.inject.Inject;

import static com.example.denis.qrapp.R.id.parent;
import static com.example.denis.qrapp.R.id.piceCard;
import static com.example.denis.qrapp.R.id.recyclerPica;

/**
 * Created by Denis on 16.2.2017..
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.DataObjectHolder> implements Serializable

{




    private Integer[] kolicina = new Integer[]{1,2,3,4,5};
    private ArrayList<String> velicina;
    private Set keySet;
    public boolean onBind;
    private Double[] cijena = new Double[]{5.00,10.00};
    private String[] jakoca = new String[]{"Mala", "velika"};
    private TextView cijenaText;
    private Spinner velicinaSpinner;
    
    private Kafic kafic;
    private ArrayList<Pice> pica;






    public static class DataObjectHolder extends RecyclerView.ViewHolder    {
        TextView naziv;
        TextView cijena;
        Spinner kolicinaSpinner;
        Spinner velicinaSpinner;
        ViewGroup parent;
        CardView cardView;
        ImageButton favouriteButton;
        ImageButton dodajButton;
        Pice holderPice;
        boolean favorit;



        public DataObjectHolder(View itemView){
            super(itemView);
            naziv = (TextView) itemView.findViewById(R.id.redNaziv);
            cijena = (TextView) itemView.findViewById(R.id.redCijena);
            kolicinaSpinner = (Spinner) itemView.findViewById(R.id.spinnerKolicina);
            velicinaSpinner = (Spinner) itemView.findViewById(R.id.spinnerVelicina);
            cardView = (CardView) itemView.findViewById(R.id.piceCard);
            favouriteButton = (ImageButton) itemView.findViewById(R.id.favouriteButton);
            dodajButton = (ImageButton) itemView.findViewById(R.id.dodajButton);
            favorit = false;



        }

        public ViewGroup getParent() {

            return parent;
        }

        public void setParent(ViewGroup parent) {
            this.parent = parent;
        }

        public void bind(int position){

        }







    }


    public RecyclerViewAdapter(ArrayList<Pice> pica){
        this.pica = pica;


    }



    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pice_red, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);


        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(final DataObjectHolder holder, int position) {

        Pice pice =pica.get(position);
        holder.holderPice = pice;

        holder.naziv.setText(pice.getNaziv());





        final Double[] polje = new Double[pice.getCijena().size()];
        for (int i = 0; i<pice.getCijena().size();i++){
            polje[i] = pice.getCijena().get(i);
        }


        Integer[] brojPica = new Integer[]{1,2,3,4,5};



        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(holder.cijena.getContext(), R.layout.spinner_dropdown,brojPica);

        holder.kolicinaSpinner.setAdapter(adapter);
        final ArrayAdapter<String> velicinaAdapter = new ArrayAdapter<String>(holder.cijena.getContext(), R.layout.siri_spinner_dropdown, pice.getKolicina());
        holder.velicinaSpinner.setTag(position);
        holder.velicinaSpinner.setAdapter(velicinaAdapter);


        //holder.velicinaSpinner.setSelection(1);
        holder.cijena.setText(pice.getCijena().get(holder.velicinaSpinner.getSelectedItemPosition())+"kn");



        //String selected = holder.velicinaSpinner.getSelectedItem().toString();
        //holder.cijena.setText(pica.get(position).getVelicinaCijena().get(selected).toString());


         holder.dodajButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 //presenter.itemDodan(holder.holderPice.getNaziv(), holder.kolicinaSpinner.getSelectedItemPosition(), holder.holderPice.getKolicina().get(holder.velicinaSpinner.getSelectedItemPosition()));

                 String naziv = holder.holderPice.getNaziv();
                 String nacin = holder.holderPice.getKolicina().get(holder.velicinaSpinner.getSelectedItemPosition());
                 int kolicina = (int) holder.kolicinaSpinner.getSelectedItemPosition()+1;
                 Double cijena = holder.holderPice.getCijena().get(holder.velicinaSpinner.getSelectedItemPosition());

                 EventBus.getDefault().post(new NarucenoPice(naziv, cijena, kolicina, nacin));


             }
         });

        holder.favouriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holder.favorit = !holder.favorit;
                if(holder.favorit){
                    holder.favouriteButton.setImageResource(R.drawable.ic_favorite_red_18dp);
                }else{
                    holder.favouriteButton.setImageResource(R.drawable.ic_favorite_black_18dp);
                }


            }
        });

        holder.velicinaSpinner.post(new Runnable() {
            @Override
            public void run() {
                holder.velicinaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        holder.cijena.setText(polje[position]+"kn");
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

            }
        });

    }




    @Override
    public int getItemCount() {
        return pica.size();
    }

    public ArrayList<Pice> getPica() {
        return pica;
    }

    public void setPica(ArrayList<Pice> pica) {
        this.pica = pica;
    }






}


