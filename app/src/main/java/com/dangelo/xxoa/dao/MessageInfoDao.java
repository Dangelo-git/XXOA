package com.dangelo.xxoa.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "MESSAGE_INFO".
*/
public class MessageInfoDao extends AbstractDao<MessageInfo, Long> {

    public static final String TABLENAME = "MESSAGE_INFO";

    /**
     * Properties of entity MessageInfo.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Msg_name = new Property(1, String.class, "msg_name", false, "MSG_NAME");
        public final static Property Msg_content = new Property(2, String.class, "msg_content", false, "MSG_CONTENT");
        public final static Property Msg_isread = new Property(3, Integer.class, "msg_isread", false, "MSG_ISREAD");
        public final static Property Msg_time = new Property(4, Long.class, "msg_time", false, "MSG_TIME");
        public final static Property Msg_type = new Property(5, Integer.class, "msg_type", false, "MSG_TYPE");
        public final static Property Msg_pic = new Property(6, String.class, "msg_pic", false, "MSG_PIC");
        public final static Property Msg_number = new Property(7, String.class, "msg_number", false, "MSG_NUMBER");
        public final static Property Msg_arg1 = new Property(8, String.class, "msg_arg1", false, "MSG_ARG1");
        public final static Property Msg_arg2 = new Property(9, String.class, "msg_arg2", false, "MSG_ARG2");
    };


    public MessageInfoDao(DaoConfig config) {
        super(config);
    }
    
    public MessageInfoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"MESSAGE_INFO\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"MSG_NAME\" TEXT," + // 1: msg_name
                "\"MSG_CONTENT\" TEXT," + // 2: msg_content
                "\"MSG_ISREAD\" INTEGER," + // 3: msg_isread
                "\"MSG_TIME\" INTEGER," + // 4: msg_time
                "\"MSG_TYPE\" INTEGER," + // 5: msg_type
                "\"MSG_PIC\" TEXT," + // 6: msg_pic
                "\"MSG_NUMBER\" TEXT," + // 7: msg_number
                "\"MSG_ARG1\" TEXT," + // 8: msg_arg1
                "\"MSG_ARG2\" TEXT);"); // 9: msg_arg2
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"MESSAGE_INFO\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, MessageInfo entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String msg_name = entity.getMsg_name();
        if (msg_name != null) {
            stmt.bindString(2, msg_name);
        }
 
        String msg_content = entity.getMsg_content();
        if (msg_content != null) {
            stmt.bindString(3, msg_content);
        }
 
        Integer msg_isread = entity.getMsg_isread();
        if (msg_isread != null) {
            stmt.bindLong(4, msg_isread);
        }
 
        Long msg_time = entity.getMsg_time();
        if (msg_time != null) {
            stmt.bindLong(5, msg_time);
        }
 
        Integer msg_type = entity.getMsg_type();
        if (msg_type != null) {
            stmt.bindLong(6, msg_type);
        }
 
        String msg_pic = entity.getMsg_pic();
        if (msg_pic != null) {
            stmt.bindString(7, msg_pic);
        }
 
        String msg_number = entity.getMsg_number();
        if (msg_number != null) {
            stmt.bindString(8, msg_number);
        }
 
        String msg_arg1 = entity.getMsg_arg1();
        if (msg_arg1 != null) {
            stmt.bindString(9, msg_arg1);
        }
 
        String msg_arg2 = entity.getMsg_arg2();
        if (msg_arg2 != null) {
            stmt.bindString(10, msg_arg2);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public MessageInfo readEntity(Cursor cursor, int offset) {
        MessageInfo entity = new MessageInfo( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // msg_name
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // msg_content
            cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3), // msg_isread
            cursor.isNull(offset + 4) ? null : cursor.getLong(offset + 4), // msg_time
            cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5), // msg_type
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // msg_pic
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // msg_number
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // msg_arg1
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9) // msg_arg2
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, MessageInfo entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setMsg_name(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setMsg_content(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setMsg_isread(cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3));
        entity.setMsg_time(cursor.isNull(offset + 4) ? null : cursor.getLong(offset + 4));
        entity.setMsg_type(cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5));
        entity.setMsg_pic(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setMsg_number(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setMsg_arg1(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setMsg_arg2(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(MessageInfo entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(MessageInfo entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
