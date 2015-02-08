package com.techyos.noorm;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

public class ContactCursorAdapter extends ResourceCursorAdapter{

    /**
     * Constructors
     */

    public ContactCursorAdapter(Context ctx, Cursor cursor, int flags) {
        super(ctx, R.layout.contact_list_item, cursor, flags);
    }

    /**
     * Overridden Methods: ResourceCursorAdapter
     */

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        String id = cursor.getString(cursor.getColumnIndex(ContactBookContract.Contact._ID));
        view.setTag(id);

        TextView contactId = (TextView) view.findViewById(R.id.contactId);
        contactId.setText(id);

        TextView contactFirstName = (TextView) view.findViewById(R.id.contactFirstName);
        contactFirstName.setText(cursor.getString(cursor.getColumnIndex(ContactBookContract.Contact.COLUMN_NAME_FIRST_NAME)));

        TextView contactLastName = (TextView) view.findViewById(R.id.contactLastName);
        contactLastName.setText(cursor.getString(cursor.getColumnIndex(ContactBookContract.Contact.COLUMN_NAME_LAST_NAME)));
    }
}
