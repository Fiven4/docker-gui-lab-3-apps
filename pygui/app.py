import tkinter as tk
from tkinter import messagebox

def add_task():
    task = task_entry.get()
    if task:
        task_listbox.insert(tk.END, task)
        task_entry.delete(0, tk.END)
    else:
        messagebox.showwarning("Внимание", "Пожалуйста, введите текст задачи!")

def delete_task():
    try:
        selected_index = task_listbox.curselection()[0]
        task_listbox.delete(selected_index)
    except IndexError:
        messagebox.showwarning("Внимание", "Выберите задачу для удаления!")

# Создание главного окна
root = tk.Tk()
root.title("Менеджер задач (Docker Python)")
root.geometry("350x450")
root.configure(bg="#f0f0f0")

# Заголовок
title_label = tk.Label(root, text="Мои задачи", font=("Arial", 16, "bold"), bg="#f0f0f0")
title_label.pack(pady=15)

# Фрейм для ввода задач
input_frame = tk.Frame(root, bg="#f0f0f0")
input_frame.pack(pady=5)

task_entry = tk.Entry(input_frame, width=20, font=("Arial", 14))
task_entry.pack(side=tk.LEFT, padx=5)

add_btn = tk.Button(input_frame, text="Добавить", command=add_task, font=("Arial", 12))
add_btn.pack(side=tk.LEFT)

# Фрейм для списка задач и скроллбара
list_frame = tk.Frame(root)
list_frame.pack(pady=15, fill=tk.BOTH, expand=True, padx=20)

scrollbar = tk.Scrollbar(list_frame)
scrollbar.pack(side=tk.RIGHT, fill=tk.Y)

task_listbox = tk.Listbox(list_frame, font=("Arial", 14), selectbackground="#a6a6a6", yscrollcommand=scrollbar.set)
task_listbox.pack(side=tk.LEFT, fill=tk.BOTH, expand=True)
scrollbar.config(command=task_listbox.yview)

# Кнопка удаления
del_btn = tk.Button(root, text="Удалить выбранное", command=delete_task, font=("Arial", 12), fg="red")
del_btn.pack(pady=10)

root.mainloop()