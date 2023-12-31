package org.zerock.service.SampleTx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.mapper.Sample1Mapper;
import org.zerock.mapper.Sample2Mapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class SampleTxServiceImpl implements SampleTxService {

   @Setter(onMethod_ = {@Autowired})
   private Sample1Mapper mapper1;

   @Setter(onMethod_ = {@Autowired})
   private Sample2Mapper mapper2;
   
   @Transactional
   @Override
   public void addData(String value) {
      mapper1.insertColl(value);
      log.info("mapper1 동작");
      
      mapper2.insertColl(value);
      log.info("mapper2 동작");
   }
}
