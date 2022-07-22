package com.gomara.Presenter;

import com.gomara.Prosecer.Comunicado;
import com.gomara.Server.ComunicadoServer;
import com.gomara.Server.ComunicadoServerImpl;
import com.gomara.ui.ComunicadoView;

import java.util.ArrayList;

public class ComunicadoPresenterImp implements ComunicadoPresenter {

    private ComunicadoView couponView;
    private ComunicadoServer server = new ComunicadoServerImpl(this);

    //Inyeccion de Dependencias
    public ComunicadoPresenterImp(ComunicadoView couponView){
        this.couponView = couponView;
    }

    @Override
    public void showUser(String anio, String curso) {
        couponView.showUser(anio, curso);
    }

    @Override
    public void showCoupons(ArrayList<Comunicado> comunicados) {
        couponView.showComunicado(comunicados);
    }

    @Override
    public void isPreseptor(boolean isPreseptor) {
        couponView.isPreseptor(isPreseptor);
    }

    @Override
    public void getUser(String uid) {
        server.getUser(uid);
    }

    @Override
    public void getCoupons(String anio,String curso) {
        server.getCouponsFirebase(anio,curso);
    }

}
