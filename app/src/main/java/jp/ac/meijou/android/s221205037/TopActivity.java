package jp.ac.meijou.android.s221205037;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import jp.ac.meijou.android.s221205037.databinding.ActivityTopBinding;

public class TopActivity extends AppCompatActivity {
    private ActivityTopBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTopBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.mealButton.setOnClickListener(view -> {
            var intent = new Intent(this, SubActivity.class);
            startActivity(intent);
        });

    }
}