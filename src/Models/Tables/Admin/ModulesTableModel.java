package Models.Tables.Admin;

import Models.CourseStructure.CourseStructure;
import Models.CourseStructure.Department;
import Models.CourseStructure.UniModule;

import javax.swing.table.AbstractTableModel;

public class ModulesTableModel extends CourseStructureTableModel {

    public ModulesTableModel(UniModule module){super(module);}

}
