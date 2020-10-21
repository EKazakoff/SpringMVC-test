package service;

import com.test.jpa.AccountsEntity;

public interface AccountEntityCRUD {
    //create(принимает значения),
    // read(должен принимать одно значение в виде ID)
    // update(принимает значения),
    // delete (должен принимать одно значение в виде ID)

    public void create(AccountsEntity ae);
    public AccountsEntity read(Integer id);
    public void update(AccountsEntity ae);
    public void delete(Integer id);
}
