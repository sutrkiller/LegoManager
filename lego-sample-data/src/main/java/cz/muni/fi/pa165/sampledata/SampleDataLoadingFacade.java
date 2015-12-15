package cz.muni.fi.pa165.sampledata;

import java.io.IOException;

/**
 * Populates database with sample data.
 *
 * @author Marek Abaffy <abaffy.m@gmail.com>
 * @since 14/12/2015.
 */
public interface SampleDataLoadingFacade {

    void loadData() throws IOException;
}
