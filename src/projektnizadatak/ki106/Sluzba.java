/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projektnizadatak.ki106;

/**
 *
 * @author Marija
 */
public enum Sluzba {
    
    STUDENTSKA_SLUZBA("Studentska sluzba"), PRAVNA_SLUZBA("Pravna sluzba"),IT_SLUZBA("IT sluzba");
    private final String vrsteSluzbe;

    private Sluzba(String vrsteSluzbe) {
        this.vrsteSluzbe = vrsteSluzbe;
    }

    @Override
    public String toString() {
        return vrsteSluzbe  ;
    }
    
}
