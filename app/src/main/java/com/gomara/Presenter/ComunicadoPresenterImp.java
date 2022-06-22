package com.gomara.Presenter;

import com.gomara.Prosecer.Comunicado;
import com.gomara.Server.ComunicadoInteractor;
import com.gomara.Server.ComunicadoInteractorImpl;
import com.gomara.UI.ComunicadoView;

import java.util.ArrayList;

public class ComunicadoPresenterImp implements ComunicadoPresenter {

    private ComunicadoView couponView;
    private ComunicadoInteractor couponsInteractor = new ComunicadoInteractorImpl(this);

    //Inyeccion de Dependencias
    public ComunicadoPresenterImp(ComunicadoView couponView){
        this.couponView = couponView;
    }

    @Override
    public void showCoupons(ArrayList<Comunicado> comunicados) {
        couponView.showComunicado(comunicados);
    }

    @Override
    public void getCoupons(String anio,String curso) {
        couponsInteractor.getCouponsFirebase(anio,curso);
    }
}
