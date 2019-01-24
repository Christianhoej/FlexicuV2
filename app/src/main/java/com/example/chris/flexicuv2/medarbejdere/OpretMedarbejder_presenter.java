
package com.example.chris.flexicuv2.medarbejdere;

/**
 * @Author Janus
 */
import com.example.chris.flexicuv2.database.DBManager;

/**
 * @Author
 */
public class OpretMedarbejder_presenter {

    private UpdateOpretMedarbejderAkt updateOpretMedarbejderAkt;
    private final static OpretMedarbejder_presenter INSTANCE = new OpretMedarbejder_presenter();

    private DBManager dbManager;



    public void setUpdateOpretMedarbejderAkt(UpdateOpretMedarbejderAkt updateOpretMedarbejderAkt) {
        this.updateOpretMedarbejderAkt = updateOpretMedarbejderAkt;
    }



    interface UpdateOpretMedarbejderAkt{

    }







}
