package com.davidread.starwarsnamegenerator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

/**
 * {@link MainActivity} provides a form for a user to input their first name, last name, city born,
 * and mother's maiden name. A generate button allows the user to get their Star Wars name given
 * these inputs.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * {@link TextInputLayout} for the first name field.
     */
    private TextInputLayout firstNameTextInputLayout;

    /**
     * {@link TextInputLayout} for the last name field.
     */
    private TextInputLayout lastNameTextInputLayout;

    /**
     * {@link TextInputLayout} for the city born field.
     */
    private TextInputLayout cityBornTextInputLayout;

    /**
     * {@link TextInputLayout} for the mother's maiden name field.
     */
    private TextInputLayout motherMaidenNameTextInputLayout;

    /**
     * {@link EditText} for the first name field.
     */
    private EditText firstNameEditText;

    /**
     * {@link EditText} for the last name field.
     */
    private EditText lastNameEditText;

    /**
     * {@link EditText} for the city born field.
     */
    private EditText cityBornEditText;

    /**
     * {@link EditText} for the mother's maiden name field.
     */
    private EditText motherMaidenNameEditText;

    /**
     * {@link Button} for generating the user's Star Wars name.
     */
    private Button generateButton;

    /**
     * Invoked to initialize the {@link MainActivity}. It simply initializes objects used in the
     * form.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.app_name);

        // Initialize all View objects used in the form.
        firstNameTextInputLayout = findViewById(R.id.first_name_text_field);
        lastNameTextInputLayout = findViewById(R.id.last_name_text_field);
        cityBornTextInputLayout = findViewById(R.id.city_born_text_field);
        motherMaidenNameTextInputLayout = findViewById(R.id.mother_maiden_name_text_field);
        firstNameEditText = firstNameTextInputLayout.getEditText();
        lastNameEditText = lastNameTextInputLayout.getEditText();
        cityBornEditText = cityBornTextInputLayout.getEditText();
        motherMaidenNameEditText = motherMaidenNameTextInputLayout.getEditText();
        generateButton = findViewById(R.id.generate_button);

        /* Add TextChangedListeners to all EditText objects such that they display an error message
         * when a string of an invalid length or content is in the field. */
        firstNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() < 2) {
                    firstNameTextInputLayout.setError(getString(R.string.string_length_error_message, "two", "s"));
                } else if (!s.toString().matches("[a-zA-Z]+")) {
                    firstNameTextInputLayout.setError(getString(R.string.string_content_error_message));
                } else {
                    firstNameTextInputLayout.setError(null);
                    firstNameTextInputLayout.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        lastNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() < 3) {
                    lastNameTextInputLayout.setError(getString(R.string.string_length_error_message, "three", "s"));
                } else if (!s.toString().matches("[a-zA-Z]+")) {
                    lastNameTextInputLayout.setError(getString(R.string.string_content_error_message));
                } else {
                    lastNameTextInputLayout.setError(null);
                    lastNameTextInputLayout.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        cityBornEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() < 3) {
                    cityBornTextInputLayout.setError(getString(R.string.string_length_error_message, "three", "s"));
                } else if (!s.toString().matches("[a-zA-Z]+")) {
                    cityBornTextInputLayout.setError(getString(R.string.string_content_error_message));
                } else {
                    cityBornTextInputLayout.setError(null);
                    cityBornTextInputLayout.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        motherMaidenNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() < 2) {
                    motherMaidenNameTextInputLayout.setError(getString(R.string.string_length_error_message, "two", "s"));
                } else if (!s.toString().matches("[a-zA-Z]+")) {
                    motherMaidenNameTextInputLayout.setError(getString(R.string.string_content_error_message));
                } else {
                    motherMaidenNameTextInputLayout.setError(null);
                    motherMaidenNameTextInputLayout.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        // Set OnClickListener for "Generate" Button.
        generateButton.setOnClickListener((v) -> generateButtonClick());
    }

    /**
     * Pops the user's Star Wars name in an {@link AlertDialog}. The name is gotten from a
     * {@link StarWarsNameGenerator} constructed from the user's inputs in the form.
     */
    public void generateButtonClick() {

        // Get first name, last name, city born, and mother's maiden name from the EditText objects.
        String firstName = firstNameEditText.getText().toString();
        String lastName = lastNameEditText.getText().toString();
        String cityBorn = cityBornEditText.getText().toString();
        String motherMaidenName = motherMaidenNameEditText.getText().toString();

        // Get Star Wars name String from StarWarsNameGenerator.
        StarWarsNameGenerator nameGenerator = new StarWarsNameGenerator(firstName, lastName, cityBorn, motherMaidenName);
        String starWarsName;
        try {
            starWarsName = nameGenerator.getStarWarsName();
        } catch (IllegalStateException e) {
            // If unsuccessful, pop error Snackbar and return early.
            Snackbar.make(generateButton, getString(R.string.form_error_message), BaseTransientBottomBar.LENGTH_SHORT).show();
            return;
        }

        // If successful, pop AlertDialog displaying the Star Wars name String.
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle(R.string.dialog_title_label)
                .setMessage(starWarsName)
                .setPositiveButton(android.R.string.ok, null);
        builder.show();
    }
}