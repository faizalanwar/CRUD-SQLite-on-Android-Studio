package com.naskahkode.finalcrud_faizalanwar;

public class Konfigurasi {

    private int id;
    private String nama;
    private String nrp;
    private byte[] foto;

    public Konfigurasi(int id, String nama, String nrp, byte[] foto) {
        this.id = id;
        this.nama = nama;
        this.nrp = nrp;
        this.foto = foto;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNrp() {
        return nrp;
    }
    public void setNrp(String nrp) {
        this.nrp = nrp;
    }

    public byte[] getFoto() {
        return foto;
    }
    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
}
