package com.techyos.noorm;

import android.provider.BaseColumns;

public final class ContactBookContract {

    /**
     * Constructors
     */

    private ContactBookContract() {
        // Should never be instantiated
    }

    /**
     * Inner Classes
     */

    public interface Contact extends BaseColumns {
        public static final String TABLE_NAME = "contact";
        public static final String COLUMN_NAME_FIRST_NAME = "firstName";
        public static final String COLUMN_NAME_LAST_NAME = "lastName";
    }
}
