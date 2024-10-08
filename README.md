# Плагин добавляет возможность оценить приложение в AppStore and Google play.

Так как у обоих магазинов есть правила использования окна оценки приложения, рекомендую добавить условия вызова функции оценки приложения. 
В обоих случаях запрос можно сделать не более 3 раз в год. Далее оно просто не будет показваться, отправляя данные об успешном запросе. Так же вы не сможете узнать оценил ли пользователь приложение и какую оценку он поставил.
Вы можете зафиксировать первый вызов функции и дальше в работе приложения проверять был ли уже вызов, например, за последние 3 месяца и дальше принимать решения для последующего вызова.

Пример вызова:
```
//index.d.ts

interface Window {
    appStoreReview: AppStoreReview;
}

interface AppStoreReview {
    appStoreReview(
        successCallback: (success: any) => void,
        failCallback: (error: string) => void
    ): void;
}

//index.tsx

export function fixStoreReview(
success_callback: (success: any) => void,
fail_callback: (error: string) => void
) {
  return window.appStoreReview.appStoreReview(success_callback, fail_callback);
  }


const start = useCallback(async () => {
        try {
            await new Promise<void>((resolve, reject) =>
                fixStoreReview(
                    (suc) => {
                        console.log('appStoreReview success', suc);
                        resolve();
                    },
                    (er) => {
                        console.log('appStoreReview error', er);
                        reject();
                    }
                )
            )
                .then(async (success) => {
                    run({    // вызываем функции для записи даты оценки и далее обрабатываем результ
                        data: new Date(),
                    });
                    console.log('appStoreReview success', success);
                    if (response?.ok) {
                        reviewAppSlice(true);
                    } else if (error) {
                        reviewAppSlice(false);
                    }
                })
                .catch((error) => {
                    console.log('appStoreReviewRun error', error);
                });
        } catch (e) {
            console.error('FAILED appStoreReview', e);
        }
    }, []);
```
