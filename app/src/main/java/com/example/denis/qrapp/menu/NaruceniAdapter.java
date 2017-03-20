package com.example.denis.qrapp.menu;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.denis.qrapp.R;
import com.example.denis.qrapp.data.NarucenoPice;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

/**
 * Created by Denis on 25.2.2017..
 */

public class NaruceniAdapter extends RecyclerView.Adapter<NaruceniAdapter.MyViewHolder> {

    private ArrayList<NarucenoPice> naruceno;
    Integer[] brojPica = new Integer[6];

    public NaruceniAdapter(ArrayList<NarucenoPice> naruceno) {
        this.naruceno = naruceno;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.naruceni_red, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {



        NarucenoPice narucenoPice = naruceno.get(position);
        if (narucenoPice.getKolicina()>3){
            for (int i = 0;i<6;i++){
            brojPica[i] = narucenoPice.getKolicina()-3+i;
                ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(holder.narucenaCijena.getContext(), R.layout.support_simple_spinner_dropdown_item,brojPica);
                holder.narucenaKolicina.setAdapter(adapter);
                holder.narucenaKolicina.setSelection(narucenoPice.getKolicina()+3);
            }
        }else{
            for (int i = 0;i<6;i++){
                brojPica[i] = i;
                ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(holder.narucenaCijena.getContext(), R.layout.support_simple_spinner_dropdown_item,brojPica);
                holder.narucenaKolicina.setAdapter(adapter);
                holder.narucenaKolicina.setSelection(narucenoPice.getKolicina());
            }
        }
        holder.naruceniNacin.setText(narucenoPice.getNacin());
        holder.naruceniNaziv.setText(narucenoPice.getNaziv());
        holder.narucenaCijena.setText(narucenoPice.getCijena()+"");

        holder.ukloniPice.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(position);
            }
        });




    }

    @Override
    public int getItemCount() {
        return naruceno.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView narucenaCijena, naruceniNaziv, naruceniNacin;
        public Spinner narucenaKolicina;
        ImageButton ukloniPice;

        public MyViewHolder(View itemView) {
            super(itemView);
            narucenaCijena = (TextView) itemView.findViewById(R.id.narucenaCijena);
            naruceniNaziv = (TextView) itemView.findViewById(R.id.naruceniNaziv);
            naruceniNacin = (TextView) itemView.findViewById(R.id.naruceniNacin);
            narucenaKolicina = (Spinner) itemView.findViewById(R.id.narucenaKolicina);
            ukloniPice = (ImageButton) itemView.findViewById(R.id.naruceniObrisi);

        }
    }
}
