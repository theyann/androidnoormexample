package com.techyos.noorm;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends ActionBarActivity implements View.OnClickListener, AdapterView.OnItemLongClickListener {

    /**
     * Attributes
     */

    private EditText editFirstName;
    private EditText editLastName;
    private Button addButton;
    private ListView contactsList;
    private ContactCursorAdapter contactsAdapter;

    private ContactDao contactDao;

    /**
     * Lifecycle
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactDao = new ContactDao(new ContactBookDbHelper(this));

        registerViews();
        registerListeners();
        setupAdapters();
    }

    /**
     * Private Methods
     */

    private void registerViews() {
        editFirstName = (EditText) findViewById(R.id.editFirstName);
        editLastName = (EditText) findViewById(R.id.editLastName);
        addButton = (Button) findViewById(R.id.addButton);
        contactsList = (ListView) findViewById(R.id.dataList);
    }

    private void registerListeners() {
        addButton.setOnClickListener(this);
        contactsList.setOnItemLongClickListener(this);
    }

    private void setupAdapters() {
        contactsAdapter = new ContactCursorAdapter(this, contactDao.getAllCursor(), 0);
        contactsList.setAdapter(contactsAdapter);
    }

    private void refreshList() {
        contactsAdapter.swapCursor(contactDao.getAllCursor());
    }

    /**
     * Overridden Methods: OnClickListener
     */

    @Override
    public void onClick(View v) {
        String firstName = editFirstName.getText().toString();
        String lastName = editLastName.getText().toString();

        contactDao.addContact(firstName, lastName);
        refreshList();

        editFirstName.setText("");
        editFirstName.requestFocus();
        editLastName.setText("");
    }

    /**
     * Overridden Methods: OnItemLongClickListener
     */

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        if (view.getTag() != null && view.getTag() instanceof String) {
            contactDao.deleteContact((String) view.getTag());
            refreshList();
            return true;
        }
        return false;
    }
}
