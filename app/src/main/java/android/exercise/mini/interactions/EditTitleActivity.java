package android.exercise.mini.interactions;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditTitleActivity extends AppCompatActivity {
    private boolean isEditing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_title);
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        // find all views
        FloatingActionButton fabStartEdit = findViewById(R.id.fab_start_edit);
        FloatingActionButton fabEditDone = findViewById(R.id.fab_edit_done);

        TextView textViewTitle = findViewById(R.id.textViewPageTitle);
        EditText editTextTitle = findViewById(R.id.editTextPageTitle);

        // setup - start from static title with "edit" button
        fabStartEdit.setVisibility(View.VISIBLE);
        fabStartEdit.setAlpha(1.0f);
        fabEditDone.setVisibility(View.GONE);
        fabEditDone.setAlpha(0.0f);
        String defaultTitle = "Page title here";
        textViewTitle.setText(defaultTitle);
        textViewTitle.setVisibility(View.VISIBLE);
        editTextTitle.setText(defaultTitle);
        editTextTitle.setVisibility(View.GONE);


        // handle clicks on "start edit"
        fabStartEdit.setOnClickListener(v -> {
            this.isEditing = true;
            fadeOut(fabStartEdit);
            fadeIn(fabEditDone);
            textViewTitle.setVisibility(View.GONE);
            editTextTitle.setVisibility(View.VISIBLE);

            // open keyboard and move cursor
            editTextTitle.requestFocus();
            editTextTitle.setSelection(editTextTitle.getText().length());
            imm.showSoftInput(editTextTitle, InputMethodManager.SHOW_FORCED);

        });

        // handle clicks on "done edit"
        fabEditDone.setOnClickListener(v -> {
            this.isEditing = false;
            fadeOut(fabEditDone);
            fadeIn(fabStartEdit);
            textViewTitle.setText(editTextTitle.getText());
            textViewTitle.setVisibility(View.VISIBLE);
            editTextTitle.setVisibility(View.GONE);

            // close keyboard
            editTextTitle.requestFocus();
            imm.hideSoftInputFromWindow(textViewTitle.getWindowToken(), 0);
        });
    }

    @Override
    public void onBackPressed() {
        // BACK button was clicked

        // find all the views like before:
        FloatingActionButton fabStartEdit = findViewById(R.id.fab_start_edit);
        FloatingActionButton fabEditDone = findViewById(R.id.fab_edit_done);
        TextView textViewTitle = findViewById(R.id.textViewPageTitle);
        EditText editTextTitle = findViewById(R.id.editTextPageTitle);
        if (this.isEditing)
        {
            this.isEditing = false;
            fadeOut(fabEditDone);
            fadeIn(fabStartEdit);
            textViewTitle.setVisibility(View.VISIBLE);
            editTextTitle.setText(textViewTitle.getText());
            editTextTitle.setVisibility(View.GONE);
        }
        else super.onBackPressed();
    }

    private void fadeOut(FloatingActionButton button)
    // a method to animate out a button
    {
        button.animate().alpha(0.0f).setDuration(1000).start();
        button.setVisibility(View.GONE);
    }

    private void fadeIn(FloatingActionButton button)
    // a method to animate in a button
    {
        button.setVisibility(View.VISIBLE);
        button.animate().alpha(1.0f).setDuration(1000).start();

    }



}


