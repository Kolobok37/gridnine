
Имеется некая система, которая обрабатывает авиа перелёты. 
Перелёт — это перевозка пассажира из одной точки в другую с возможными промежуточными посадками. Т. о. перелёт можно представить как набор из одного или нескольких элементарных перемещений, называемых сегментами. 
Сегмент — это атомарная перевозка, которую для простоты будем характеризовать всего двумя атрибутами: дата/время вылета и дата/время прилёта.
Разработан модуль фильтрации, который имеет возможность фильтровать по следующим параметрам:
    1. вылет до текущего момента времени
    2. имеются сегменты с датой прилёта раньше даты вылета
    3. общее время, проведённое на земле превышает два часа (время на земле — это интервал между прилётом одного сегмента и вылетом следующего за ним)
