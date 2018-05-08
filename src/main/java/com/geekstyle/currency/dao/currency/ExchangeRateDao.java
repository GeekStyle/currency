package com.geekstyle.currency.dao.currency;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.geekstyle.currency.model.currency.ExchangeRate;

@Mapper
public interface ExchangeRateDao {
	
	@Select({"select id,base,currency,rate from exchange_rate where base=#{base} and currency = #{currency}"})
	public ExchangeRate getExchangeRate(ExchangeRate exchangeRate);
	
	@Select({"select id,base,currency,rate,update_time updateTime from exchange_rate"})
	public List<ExchangeRate> getAllExchangeRate();
	
	@Update("truncate table exchange_rate")
	public void truncateExchangeRate();
	
	@Insert({"insert into exchange_rate (base,currency,rate,update_time) values (#{base},#{currency},#{updateTime})"})
	public void insertExchangeRate(ExchangeRate exchangeRate);
	
	@Insert({
        "<script>",
        "insert into exchange_rate (base,currency,rate,update_time)",
        "values ",
        "<foreach  collection='list' item='item' separator=','>",
        	"(#{item.base},#{item.currency},#{item.rate},#{item.updateTime})",
        "</foreach>",
        "</script>"
	})
	public void batchInsertExchangeRate(@Param("list") List<ExchangeRate> exchangeRateList);
	
}
