package com.example.chris.flexicuv2.StartSkærm;

public class StartSkærmPresenter {
    UpdateStartskærm updateSs;

    /**
     * @author Janus
     * @param updateSs instans af StartSkærmPresenter.UpdateStartSkærm, som presenteren skal kobles sammen med.
     */
    public StartSkærmPresenter(UpdateStartskærm updateSs){
    this.updateSs = updateSs;
    }
    //TODO lav alle metoder der skal anvendes til at opdatere viewet

    /**
     * Interfacet implementeres af Startskærm for at kunne hente fra/opdatere viewet.
     *
     */
    public interface UpdateStartskærm{
        void updateRecyclerView();
        void updateRecyclerView2();
        void updateRecyclerView3();
    }
}
