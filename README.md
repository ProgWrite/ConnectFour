Смысл этой игры состоит в том, чтобы поместить четыре фишки в прямой ряд вертикально, горизонтально или по диагонали. 
Игра играется на квадратной доске 8x8 (в стандартном варианте используется 8 на 8, но в проекте можно выбрать другой размер для карты) и начинается с пустой доски.
Оба игрока помещают свои фишки (за ход одна фишка) на доску. Правило в том, что фишки всегда "падают".
Это означает, что фишка может быть помещена или на нижнем ряду или на другом ряду над другой фишкой в той же колонке.
**Игра заканчивается, если одно из следующих условий выполнено:**
- Игрок одного цвета помещает четыре или больше фишек в прямой ряд вертикально, горизонтально или по диагонали. Этот игрок выигрывает игру.
- Все клетки доски заняты и ни один игрок, не выполняет предыдущее условие победы. В этом случае игра заканчивается ничьёй.

```mermaid
graph TD
    Start[Начало процесса] --> GenerateSignature[Формирование CMS-подписи (PKCS#7, DER)]
    GenerateSignature --> BuildMessage[Формирование Message (FromBoxId, ToBoxId, Entities, SignedContent)]
    BuildMessage --> PostMessage[Отправка документа через postMessage]

    PostMessage --> DiadocUI[Документ доступен контрагенту в интерфейсе Диадока]
    DiadocUI --> UserAction{Контрагент: подписать или отклонить?}

    UserAction -->|Подписал| Scheduler[Фоновый планировщик @Scheduled]
    UserAction -->|Отклонил| Scheduler

    Scheduler --> GetEvents[Вызов GetPartnerEvents (с учетом LastCursor)]
    GetEvents --> EventsResponse[GetPartnerEventsResponse]

    EventsResponse --> DocWithFlow[Получение DocumentWithDocflowV4]
    DocWithFlow --> Docflow[Чтение DocflowV4]
    Docflow --> RecipientResponse[Чтение RecipientResponse (ParticipantResponseDocflowV4)]

    RecipientResponse --> StatusCheck{Статус документа?}
    StatusCheck -->|Подписан| Signed[Обновить статус: Документ подписан]
    StatusCheck -->|Отклонён| Rejected[Обновить статус: Документ отклонён]
```
