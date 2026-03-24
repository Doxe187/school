'use client'

import { useEffect, useState } from 'react'
import s from './page.module.css'

const API = 'http://localhost:8080'

export default function Home() {
  const [customers, setCustomers] = useState([])
  const [error, setError] = useState(null)
  const [editId, setEditId] = useState(null)
  const [editData, setEditData] = useState({ firstName: '', lastName: '' })
  const [newData, setNewData] = useState({ firstName: '', lastName: '' })
  const [adding, setAdding] = useState(false)

  async function load() {
    try {
      const res = await fetch(`${API}/people`)
      if (!res.ok) throw new Error('Fehler beim Laden')
      setCustomers(await res.json())
      setError(null)
    } catch (e) {
      setError(e.message)
    }
  }

  useEffect(() => { load() }, [])

  async function handleAdd(e) {
    e.preventDefault()
    if (!newData.firstName.trim() || !newData.lastName.trim()) return
    setAdding(true)
    try {
      const res = await fetch(`${API}/people`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(newData),
      })
      if (!res.ok) throw new Error('Fehler beim Erstellen')
      setNewData({ firstName: '', lastName: '' })
      await load()
    } catch (e) {
      setError(e.message)
    } finally {
      setAdding(false)
    }
  }

  function startEdit(c) {
    setEditId(c.id)
    setEditData({ firstName: c.firstName, lastName: c.lastName })
  }

  async function handleSave(id) {
    try {
      const res = await fetch(`${API}/people/${id}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(editData),
      })
      if (!res.ok) throw new Error('Fehler beim Speichern')
      setEditId(null)
      await load()
    } catch (e) {
      setError(e.message)
    }
  }

  async function handleDelete(id) {
    try {
      const res = await fetch(`${API}/people/${id}`, { method: 'DELETE' })
      if (!res.ok) throw new Error('Fehler beim Löschen')
      await load()
    } catch (e) {
      setError(e.message)
    }
  }

  return (
    <main className={s.wrapper}>
      <div className={s.header}>
        <h1>Customers <span>{customers.length}</span></h1>
      </div>

      {error && <p className={s.error}>{error}</p>}

      <table className={s.table}>
        <thead>
          <tr>
            <th>Vorname</th>
            <th>Nachname</th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          {customers.length === 0 && (
            <tr><td colSpan={3} className={s.empty}>Keine Einträge</td></tr>
          )}
          {customers.map(c =>
            editId === c.id ? (
              <tr key={c.id}>
                <td>
                  <input
                    className={s.editInput}
                    value={editData.firstName}
                    onChange={e => setEditData(d => ({ ...d, firstName: e.target.value }))}
                    autoFocus
                  />
                </td>
                <td>
                  <input
                    className={s.editInput}
                    value={editData.lastName}
                    onChange={e => setEditData(d => ({ ...d, lastName: e.target.value }))}
                    onKeyDown={e => e.key === 'Enter' && handleSave(c.id)}
                  />
                </td>
                <td className={s.actions}>
                  <button className={s.btnSave} onClick={() => handleSave(c.id)}>Speichern</button>
                  <button className={s.btnCancel} onClick={() => setEditId(null)}>Abbrechen</button>
                </td>
              </tr>
            ) : (
              <tr key={c.id}>
                <td>{c.firstName}</td>
                <td>{c.lastName}</td>
                <td className={s.actions}>
                  <button className={s.btnEdit} onClick={() => startEdit(c)}>Bearbeiten</button>
                  <button className={s.btnDelete} onClick={() => handleDelete(c.id)}>Löschen</button>
                </td>
              </tr>
            )
          )}
        </tbody>
      </table>

      <div className={s.addForm}>
        <h2>Neu anlegen</h2>
        <form onSubmit={handleAdd}>
          <div className={s.fields}>
            <input
              className={s.input}
              placeholder="Vorname"
              value={newData.firstName}
              onChange={e => setNewData(d => ({ ...d, firstName: e.target.value }))}
            />
            <input
              className={s.input}
              placeholder="Nachname"
              value={newData.lastName}
              onChange={e => setNewData(d => ({ ...d, lastName: e.target.value }))}
            />
            <button className={s.btnAdd} type="submit" disabled={adding}>
              {adding ? '...' : 'Hinzufügen'}
            </button>
          </div>
        </form>
      </div>
    </main>
  )
}
