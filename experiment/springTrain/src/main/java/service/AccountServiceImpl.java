package service;

import dao.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDao accountDao;
    @Autowired
    private LogService logService;

    @Override
    public void transfer(String out, String in, Double money) {
        // 存在结构try-finally
        try {
            accountDao.reduce(money, out);
//        int i=9/0;
            accountDao.add(money, in);
        } finally {
            logService.log(out, in, money);
        }
    }
}
