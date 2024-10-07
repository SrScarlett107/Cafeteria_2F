package com.example.mobillecoffeteria3.Fragment;

import static com.example.mobillecoffeteria3.Main.ConexaoSqlServer.conectar;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.mobillecoffeteria3.Main.MainActivity;
import com.example.mobillecoffeteria3.R;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CupomFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CupomFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CupomFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CupomFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CupomFragment newInstance(String param1, String param2) {
        CupomFragment fragment = new CupomFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    private Button generateButton;
    private TextView couponTextView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cupom, container, false);

        ImageButton button = view.findViewById(R.id.generateCouponButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateCoupon(); inserirCupom(couponCode);
            }
        });
        couponTextView = view.findViewById(R.id.couponTextView);
        // Inflate the layout for this fragment
        return view;
    }
    String couponCode = generateRandomCouponCode();
    private void generateCoupon() {
        // Lógica para gerar o cupom

        // Aqui você pode mostrar o cupom no UI, salvar em um banco de dados, etc.
        Toast.makeText(getActivity(), "Cupom gerado: " + couponCode, Toast.LENGTH_SHORT).show();
        couponTextView.setText("Seu cupom: " + couponCode);
    }
    private static String generateRandomCouponCode() {
        Random random = new Random();
        int min = 10000; // Menor número com 5 dígitos
        int max = 99999; // Maior número com 5 dígitos
        return String.valueOf(random.nextInt(max - min + 1) + min);
    }
    public void inserirCupom(String couponCode) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = conectar();
            if (connection != null) {
                String sql = "INSERT INTO cupons (codigo) VALUES (?)";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, couponCode);
                preparedStatement.executeUpdate();
                System.out.println("Cupom inserido com sucesso!");
            } else {
                System.out.println("Falha ao conectar ao banco de dados.");
            }
        } catch (SQLException e) {
            // Better error handling
            System.err.println("Erro ao inserir cupom: " + e.getMessage());
        } finally {
            // Close resources in reverse order
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
    }
}

