package com.example.mobillecoffeteria3.Main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.mobillecoffeteria3.Fragment.BlankFragment;
import com.example.mobillecoffeteria3.Fragment.CupomFragment;
import com.example.mobillecoffeteria3.Fragment.HomeFragment;
import com.example.mobillecoffeteria3.R;

import java.sql.Connection;
import java.sql.SQLException;


public class MainActivity extends AppCompatActivity {

    private ImageButton buttonPromo, buttonHome, buttonCupom;
    private BlankFragment blankFragment;
    private HomeFragment homeFragment;
    private CupomFragment cupomFragment;

    TextView BancoTeste;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        buttonHome = findViewById(R.id.buttonHome);
        buttonCupom = findViewById(R.id.buttonCupom);
        buttonPromo = findViewById(R.id.buttonPromo);

        homeFragment = new HomeFragment();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameConteudo, homeFragment);
        transaction.commit();

        buttonPromo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blankFragment = new BlankFragment();

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frameConteudo, blankFragment);
                transaction.commit();


                Connection conn = ConexaoSqlServer.conectar();
                BancoTeste = findViewById(R.id.BancoTeste);
                try{
                    if(conn != null){
                        if(!conn.isClosed())
                            BancoTeste.setText("Conexão realizada com sucesso");
                        else BancoTeste.setText("A conexão está fechada");
                    }
                    else{
                        BancoTeste.setText("Conexão nula, não estabelecida");
                    }
                } catch (java.sql.SQLException e) {
                    e.printStackTrace();
                    BancoTeste.setText("Conexão falhou\n" + e.getMessage());
                }


            }
        });

        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeFragment = new HomeFragment();

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frameConteudo, homeFragment);
                transaction.commit();

            }
        });
        buttonCupom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cupomFragment = new CupomFragment();

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frameConteudo, cupomFragment);
                transaction.commit();
            }
        });



    }
}