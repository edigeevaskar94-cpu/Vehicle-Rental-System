package adapter;

public class LegacyPaymentGateway {
    // Старый метод с несовместимым интерфейсом
    public boolean makeTransaction(double totalAmount) {
        // Имитируем отказ оплаты, если сумма меньше или равна 0, либо больше 10000 (например, лимит)
        if (totalAmount <= 0 || totalAmount > 10000) {
            System.out.println("[LegacyGateway] Ошибка: Недопустимая сумма транзакции (" + totalAmount + ")");
            return false;
        }
        System.out.println("[LegacyGateway] Успешная транзакция на сумму: $" + totalAmount);
        return true;
    }
}