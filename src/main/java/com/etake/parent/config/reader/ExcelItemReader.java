package com.etake.parent.config.reader;

import org.springframework.batch.extensions.excel.RowMapper;
import org.springframework.batch.extensions.excel.poi.PoiItemReader;
import org.springframework.batch.extensions.excel.support.rowset.DefaultRowSetFactory;
import org.springframework.batch.extensions.excel.support.rowset.RowNumberColumnNameExtractor;
import org.springframework.batch.extensions.excel.support.rowset.RowSetFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

@Component
public class ExcelItemReader {
    public <R> PoiItemReader<R> getReader(final String filePath,
                                                   final RowMapper<R> rowMapper,
                                                   final int skipLines,
                                                   final int headerRowIndex) {
        final PoiItemReader<R> reader = new PoiItemReader<>();
        reader.setResource(new FileSystemResource(filePath));
        reader.setRowMapper(rowMapper);
        reader.setLinesToSkip(skipLines);
        reader.setRowSetFactory(getRowSetFactory(headerRowIndex));
        return reader;
    }

    private static RowSetFactory getRowSetFactory(final int headerRowIndex) {
        final RowNumberColumnNameExtractor extractor = new RowNumberColumnNameExtractor();
        final DefaultRowSetFactory rowSetFactory = new DefaultRowSetFactory();
        extractor.setHeaderRowNumber(headerRowIndex);
        rowSetFactory.setColumnNameExtractor(extractor);
        return rowSetFactory;
    }
}

