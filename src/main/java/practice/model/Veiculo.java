package practice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;


@Entity
public class Veiculo implements Serializable {

    @Id
    @GeneratedValue
    private Integer idveiculo;

    private String marca;
    private String modelo;
    private String status;
    private String url;
    private String categoria;
    private float price;
    private String information;

    public Veiculo() {
    }

    public Veiculo(Integer idveiculo, String marca, String modelo, String status, String url, String categoria, float price, String information) {
        //this.idveiculo = idveiculo;
        this.marca = marca;
        this.modelo = modelo;
        this.status = status;
        this.url = url;
        this.categoria = categoria;
        this.price = price;
        this.information = information;
    }

    public Integer getIdveiculo() {
        return this.idveiculo;
    }

    public void setIdveiculo(Integer idveiculo) {
        this.idveiculo = idveiculo;
    }
    public String getMarca() {
        return this.marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
    public String getModelo() {
        return this.modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    @Override
    public String toString() {
        return "Veiculo{" +
                "idveiculo=" + idveiculo +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", status='" + status + '\'' +
                ", url='" + url + '\'' +
                ", categoria='" + categoria + '\'' +
                ", price=" + price +
                ", information='" + information + '\'' +
                '}';
    }
}