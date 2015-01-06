package pl.codeleak.demo.core;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(namespace = "pl.codeleak.demo.health")
public class Health {

    private String status;

    protected Health() {
    }

    public Health(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
