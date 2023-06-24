package de.hft_stuttgart.cpl;

public class CellReferenceType {

    private String column;
    private String row;

    public CellReferenceType(String column, String row) {
        this.column = column;
        this.row = row;
    }

    public String getColumn() {
        return column;
    }

    public String getRow() {
        return row;
    }

    public Double getColumnAsDouble() throws NumberFormatException {
        return Double.parseDouble(column);
    }

    public Double getRowAsDouble() throws NumberFormatException {
        return Double.parseDouble(row);
    }

    public String getReference() {
        return "R" + row + "C" + column;
    }

    public boolean isParseAble() {
        try {
            getColumnAsDouble();
            getRowAsDouble();
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    @Override
    public String toString() {
        return "CellReferenceType{" +
                "column='" + column + '\'' +
                ", row='" + row + '\'' +
                '}';
    }
}
