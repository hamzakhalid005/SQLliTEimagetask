import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class DatabaseHandler extends  SQLiteOpenHelper {
 Context context;
 private static String DATABASE_NAME="mydb.db";
 private static int DATABASE_VERSION=1;
 private static String createtableQuery ="create table imageinfo (imageName TEXT" +
         ",image BLOB)";
 private ByteArrayOutputStream objectByteArrayOutputStream;
 private byte[] imageinbytes;
    public DatabaseHandler( Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(createtableQuery);
            Toast.makeText(context,"table created succesfully",Toast.LENGTH_LONG).show();
        }
        catch (Exception e){
            Toast.makeText(context, e.getMessage(),Toast.LENGTH_LONG).show();

        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void storeimage(ModelClass objectmodelClass){

        try
        {
        SQLiteDatabase objectSQLiteDatabase=this.getWritableDatabase();
            Bitmap imagetostoreBitmap=objectmodelClass.getImage();
            objectByteArrayOutputStream=new ByteArrayOutputStream();
            imagetostoreBitmap.compress(Bitmap.CompressFormat.JPEG,100,objectByteArrayOutputStream);

            imageinbytes=objectByteArrayOutputStream.toByteArray();
            ContentValues objcontentValues=new ContentValues();
            objcontentValues.put("imageName",objectmodelClass.getImagename());
            objcontentValues.put("image",imageinbytes);

            long checkIfqueryruns=objectSQLiteDatabase.insert("imageinfo",null,objcontentValues);
            if(checkIfqueryruns!=-1)
            {
                Toast.makeText(context,"data added into table",Toast.LENGTH_LONG).show();
                objectSQLiteDatabase.close();
            }
            else
            {

                Toast.makeText(context,"fails to added data",Toast.LENGTH_LONG).show();
            }

        }
        catch (Exception e){
            Toast.makeText(context,e.getMessage(),Toast.LENGTH_LONG).show();


        }
    }
}
