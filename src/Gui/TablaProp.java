package Gui;

import Juego.Propiedad;

import javax.swing.table.AbstractTableModel;

public class TablaProp extends AbstractTableModel {
    private java.util.List<Propiedad> propiedades;

    public TablaProp() {
        this.propiedades = new java.util.ArrayList<>();
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public int getRowCount() {
        return propiedades.size();
    }

    @Override
    public String getColumnName(int col) {
        return "Propiedades";
    }

    @Override
    public Class getColumnClass(int col) {
        return java.lang.String.class;
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    @Override
    public Object getValueAt(int row, int col) {
        return this.propiedades.get(row);
    }

    public java.util.List<Propiedad> getFilas() {
        return this.propiedades;
    }

    public void setFilas(java.util.List<Propiedad> propiedades) {
        this.propiedades = propiedades;
        fireTableDataChanged();
    }


}
