public class ClosedRegistrationState implements RegistrationState {
    @Override
    public void openRegistration(Race race) {
        race.setRegistrationState(new OpenRegistrationState());
    }

    @Override
    public void closeRegistration(Race race){
         //throw new IllegalStateException("Registration is already closed."); // need to implement catch
    }

    @Override
    public boolean register(){
        return false;
    }
}
